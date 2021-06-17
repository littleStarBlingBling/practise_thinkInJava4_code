package com.practise.annotations;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.Set;

// 指定注解处理器关联的注解与 Java 版本
@SupportedAnnotationTypes("com.practise.annotations.Simple")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 这里只有一个注解
        for (TypeElement typeElement : annotations) {
            System.out.println(typeElement);
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(Simple.class)) {
            display(element);
        }

        return false;
    }

    private void display(Element element) {
        System.out.println("====== " + element + " ======");
        System.out.println(element.getKind() + ":"
                + element.getModifiers() + ":"
                + element.getSimpleName() + ":"
                + element.asType());

        if(element.getKind().equals(ElementKind.CLASS)){
            TypeElement typeElement = (TypeElement)element;
            System.out.println(typeElement.getQualifiedName());
            System.out.println(typeElement.getSuperclass());
            System.out.println(typeElement.getEnclosedElements());
        }

        if(element.getKind().equals(ElementKind.METHOD)){
            ExecutableElement executableElement = (ExecutableElement)element;
            System.out.println(executableElement.getReturnType());
            System.out.println(executableElement.getSimpleName());
            System.out.println(executableElement.getParameters());
        }
    }
}
