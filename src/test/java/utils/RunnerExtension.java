package utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import utils.Attach;

import static utils.Attach.attach;


public class RunnerExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean testResult = context.getExecutionException().isPresent();
        if (testResult)
            attach();
        System.out.println(testResult); //false - SUCCESS, true - FAILED
    }

}
