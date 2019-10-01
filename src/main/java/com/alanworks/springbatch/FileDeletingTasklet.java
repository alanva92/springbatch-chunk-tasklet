package com.alanworks.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.File;

public class FileDeletingTasklet implements Tasklet, InitializingBean {


    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
            throws Exception {

        System.out.println(file.getAbsolutePath());//.getResource("sample-data.csv").toString());
        //Assert.state(file.isFile());
        boolean deleted = file.delete();
        if (!deleted) {
            throw new UnexpectedJobExecutionException(
                    "Could not delete file " + file.getPath());
        } else {
            System.out.println(file.getPath() + " is deleted!");
        }



        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(file, "file does not exist");
    }

}
