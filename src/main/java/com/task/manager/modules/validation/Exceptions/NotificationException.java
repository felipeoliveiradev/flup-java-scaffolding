package com.task.manager.modules.validation.Exceptions;
import com.task.manager.modules.validation.handlers.Notification;

public class NotificationException extends DomainException {
    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
