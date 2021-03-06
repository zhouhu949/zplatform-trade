/* 
 * TestTradeThreadPool.java  
 * 
 * version TODO
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.timeout.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.trade.pool.IThreadPool;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年7月20日 上午11:10:11
 * @since 
 */
public class TradeQueueThreadPool implements IThreadPool{
    private static final Log log = LogFactory.getLog(TradeQueueThreadPool.class);
    private static ExecutorService executorService;
    private static TradeQueueThreadPool pool;
    private TradeQueueThreadPool(){
        if(executorService==null){
            executorService = Executors.newCachedThreadPool();
        }else{
            if(executorService.isShutdown()||executorService.isTerminated()){
                executorService = Executors.newCachedThreadPool();
            }
            
        }
    }
    
    public static synchronized TradeQueueThreadPool getInstance(){
        if(pool==null){
            pool = new TradeQueueThreadPool();
        }
        return pool;
    }
    
    public void executeMission(Runnable runnable){
        if(executorService.isShutdown()||executorService.isTerminated()){
            executorService = Executors.newCachedThreadPool();
        }
        log.info("thread pool :"+executorService);
        
        executorService.execute(runnable);
        log.info("thread pool :"+executorService);
        //runnable.run();
    }
}
