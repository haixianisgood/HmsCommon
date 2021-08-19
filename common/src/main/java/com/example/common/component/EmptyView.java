package com.example.common.component;

import com.example.common.ResourceTable;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;

import java.util.Timer;
import java.util.TimerTask;

public class EmptyView extends ComponentContainer implements PlaceholderView {
    private static final int EVENT_ID = 12654;

    private Image image;
    private Text text;
    private RoundProgressBar progressBar;
    private EventHandler eventHandler;
    private Timer timer;
    private TimerTask timerTask;

    public EmptyView(Context context) {
        super(context);
        init(null, "");
    }

    public EmptyView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init(attrSet, "");
    }

    private void init(AttrSet attrSet, String styleName) {
        LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_empty_view,
                this, true);

        image = (Image) findComponentById(ResourceTable.Id_image_empty);
        text = (Text) findComponentById(ResourceTable.Id_text_loading);
        progressBar = (RoundProgressBar) findComponentById(ResourceTable.Id_loading_progress);
    }

    @Override
    public void showEmpty() {
        image.setVisibility(HIDE);
        text.setVisibility(HIDE);
        progressBar.setVisibility(HIDE);
        stopProgressBar();
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError(String message) {
        image.setVisibility(HIDE);
        progressBar.setVisibility(HIDE);
        text.setText(message);
    }

    @Override
    public void showNetworkError() {
        text.setVisibility(HIDE);
        progressBar.setVisibility(HIDE);

    }

    @Override
    public void showLoading() {
        image.setVisibility(HIDE);
        text.setVisibility(HIDE);
        startProgressBar();
    }

    private void startProgressBar() {
        eventHandler = new EventHandler(EventRunner.current()) {
            @Override
            protected void processEvent(InnerEvent event) {
                if (event.eventId == EVENT_ID) {
                    float start = progressBar.getStartAngle();
                    progressBar.setStartAngle(start+10);
                }
            }
        };

        timerTask = new TimerTask() {
            @Override
            public void run() {
                eventHandler.sendEvent(EVENT_ID);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 50);
    }

    @Override
    public void showResult(boolean success) {
        if(success) {
            showSuccess();
        } else {
            showError("加载失败，请重试");
        }
    }

    private void stopProgressBar() {
        if(timer != null && timerTask != null) {
            timer.cancel();
            timer = null;
            timerTask = null;
        }
    }
}
