package com.postop.model;

/**
 *
 */
public class Notification {
    private String label;
    private int start;
    private int end;
    private int interval;

    /**
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return
     */
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * @return
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }
}
