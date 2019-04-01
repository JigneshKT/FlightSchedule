package jigneshkt.test.com.testproject.base;

public interface DataView<ActionListenerType> {

    void showLoading(boolean hideContent);

    void hideLoading();

    void showError(String message, int duration);

    void showErrorWithAction(String message, ActionListenerType listener, String actionLabel);
}
