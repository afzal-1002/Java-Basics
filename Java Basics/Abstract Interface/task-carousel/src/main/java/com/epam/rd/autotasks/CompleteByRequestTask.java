package com.epam.rd.autotasks;



public class CompleteByRequestTask implements Task {

    private boolean completeRequested;
    private boolean finished;

    public CompleteByRequestTask() {
        completeRequested = false;
        finished = false;
    }

    @Override
    public void execute() {
        if (completeRequested) {
            this.finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    public void complete() {  completeRequested = true; }
}