package org.nofdev.logging

import groovy.transform.CompilationUnitAware
import groovy.transform.CompileStatic
import groovyjarjarasm.asm.Opcodes
import org.codehaus.groovy.GroovyBugError
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.classgen.VariableScopeVisitor
import org.codehaus.groovy.control.CompilationUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import java.lang.reflect.Modifier

/**
 * Created by Liutengfei on 2017/7/24 0024.
 */
@CompileStatic
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class LoggingASTTransformation extends AbstractASTTransformation implements CompilationUnitAware {
    private CompilationUnit compilationUnit
    private static final String LOGGER_NAME = "org.nofdev.logging.CustomLogger";
    private static final String FACTORY_NAME = "org.nofdev.logging.CustomLogger";

    @Override
    void setCompilationUnit(CompilationUnit unit) {
        this.compilationUnit = unit
    }

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        init(nodes, source)

        AnnotationNode logAnnotation = (AnnotationNode) nodes[0]
        AnnotatedNode targetClass = (AnnotatedNode) nodes[1]

        if (!(targetClass instanceof ClassNode))
            throw new GroovyBugError("Class annotation " + logAnnotation.getClassNode().getName() + " annotated no Class, this must not happen.");

        final String logFieldName = lookupLogFieldName(logAnnotation)
        final ClassNode classNode = (ClassNode) targetClass;

        final GroovyClassLoader classLoader = compilationUnit != null ? compilationUnit.getTransformLoader() : source.getClassLoader()

        ClassCodeExpressionTransformer transformer = new ClassCodeExpressionTransformer() {

            @Override
            protected SourceUnit getSourceUnit() {
                return source;
            }

            @Override
            public void visitClass(ClassNode node) {
                FieldNode logField = node.getField(logFieldName);
                if (logField != null && logField.getOwner() == node) {
                    addError("Class annotated with Log annotation cannot have log field declared", logField);
                } else if (logField != null && !Modifier.isPrivate(logField.getModifiers())) {
                    addError("Class annotated with Log annotation cannot have log field declared because the field exists in the parent class: " + logField.getOwner().getName(), logField);
                } else {
                    int modifiers = Opcodes.ACC_FINAL | Opcodes.ACC_TRANSIENT | Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE
                    Expression initialValue = new MethodCallExpression(new ClassExpression(classNodeLoader(FACTORY_NAME)), "getLogger", new ConstantExpression(node.getName()))
                    node.addField(logFieldName, modifiers, classNodeLoader(LOGGER_NAME), initialValue);
                }
                super.visitClass(node);
            }

            private ClassNode classNodeLoader(String name) {
                ClassLoader cl = classLoader == null ? this.getClass().getClassLoader() : classLoader;
                try {
                    return ClassHelper.make(Class.forName(name, false, cl));
                } catch (ClassNotFoundException e) {
                    throw new GroovyRuntimeException("Unable to load logging class", e);
                }
            }
        }
        transformer.visitClass(classNode);

        // GROOVY-6373: references to 'log' field are normally already FieldNodes by now, so revisit scoping
        new VariableScopeVisitor(sourceUnit, true).visitClass(classNode);
    }

    private String lookupLogFieldName(AnnotationNode logAnnotation) {
        Expression member = logAnnotation.getMember("value")
        if (member != null && member.getText() != null) {
            return member.getText()
        } else {
            return "log"
        }
    }
}
