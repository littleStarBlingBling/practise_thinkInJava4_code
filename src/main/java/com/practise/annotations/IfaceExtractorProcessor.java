package com.practise.annotations;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;

@SupportedAnnotationTypes("com.practise.annotations.ExtractInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class IfaceExtractorProcessor extends AbstractProcessor {

    private ArrayList<Element> interfaceMethods = new ArrayList<>();
    private Elements elementUtils;
    private ProcessingEnvironment processingEnv;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        for (Element elem : env.getElementsAnnotatedWith(ExtractInterface.class)) {
            String interfaceName = elem.getAnnotation(ExtractInterface.class).interfaceName();

            // 抽取出 public static 修饰的方法
            for (Element enclosed : elem.getEnclosedElements()) {
                if (enclosed.getKind().equals(ElementKind.METHOD) && enclosed.getModifiers().contains(Modifier.PUBLIC) && !enclosed.getModifiers().contains(Modifier.STATIC)) {
                    interfaceMethods.add(enclosed);
                }
            }

            if (interfaceMethods.size() > 0) {
                writeInterfaceFile(interfaceName);
            }
        }
        return false;
    }

    private void writeInterfaceFile(String interfaceName) {
        try (Writer writer = processingEnv.getFiler().createSourceFile(interfaceName).openWriter()) {
            String packageName = elementUtils.getPackageOf(interfaceMethods.get(0)).toString();
            writer.write("package " + packageName + ";\n");

            writer.write("public interface " + interfaceName + " {\n");
            // 开始生成方法
            for (Element elem : interfaceMethods) {
                ExecutableElement method = (ExecutableElement) elem;
                String signature = " public ";
                signature += method.getReturnType() + " ";
                signature += method.getSimpleName();
                signature += createArgList(
                        method.getParameters());
                System.out.println(signature);
                writer.write(signature + ";\n");
            }
            writer.write("}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createArgList(List<? extends VariableElement> parameters) {
        String args = parameters.stream().map(p -> p.asType() + " " + p.getSimpleName()).collect(Collectors.joining(", "));
        return "(" + args + ")";
    }
}
