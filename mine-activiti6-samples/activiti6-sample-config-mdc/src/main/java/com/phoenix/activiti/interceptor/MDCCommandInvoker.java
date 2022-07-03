package com.phoenix.activiti.interceptor;

import com.phoenix.activiti.delegate.MDCErrorDelegate;
import org.activiti.engine.debug.ExecutionTreeUtil;
import org.activiti.engine.impl.agenda.AbstractOperation;
import org.activiti.engine.impl.interceptor.DebugCommandInvoker;
import org.activiti.engine.logging.LogMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mdc 拦截器
 */
public class MDCCommandInvoker extends DebugCommandInvoker {
    private static final Logger LOGGER = LoggerFactory.getLogger(MDCErrorDelegate.class);

    @Override
    public void executeOperation(Runnable runnable) {
        LOGGER.info("in --------------");
        boolean mdcEnabled = LogMDC.isMDCEnabled();
        //临时打开mdc
        LogMDC.setMDCEnabled(true);
        if (runnable instanceof AbstractOperation) {
            //是可操作的流程对象
            AbstractOperation operation = (AbstractOperation) runnable;
            if (operation.getExecution() != null) {
                LogMDC.putMDCExecution(operation.getExecution());
            }
        }
        super.executeOperation(runnable);
        //清空mdc
        LogMDC.clear();
        if (!mdcEnabled) {
            //原来的mdc是不生效的，还原原来的配置
            LogMDC.setMDCEnabled(false);
        }
    }

}
