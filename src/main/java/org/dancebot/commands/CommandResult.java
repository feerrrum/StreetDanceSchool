package org.dancebot.commands;

import java.util.List;

public class CommandResult {
    private String result;
    private List<String> multipleResults;
    private List<String> buttons;

    public CommandResult(String result) {
        this.result = result;
    }
    public CommandResult(List<String> results) {
        this.multipleResults = results;
    }
    public CommandResult(String result, List<String> buttons) {
        this.result = result;
        this.buttons = buttons;
    }
    public CommandResult(List<String> results, List<String> buttons) {
        this.multipleResults = results;
        this.buttons = buttons;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public List<String> getMultipleResults() {
        return multipleResults;
    }
    public List<String> getButtons() {
        return buttons;
    }

    public boolean hasButtons() {
        return buttons != null;
    }
}