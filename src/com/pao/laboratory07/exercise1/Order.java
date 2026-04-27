package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;

import java.util.Stack;

public class Order {
    private OrderState currentState;
    private final Stack<OrderState> history;

    public Order(OrderState initialState) {
        this.currentState = initialState;
        this.history = new Stack<>();
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void nextState() {
        if (currentState == OrderState.DELIVERED || currentState == OrderState.CANCELED) {
            throw new OrderIsAlreadyFinalException();
        }
        history.push(currentState);
        if (currentState == OrderState.PLACED) {
            currentState = OrderState.PROCESSED;
        } else if (currentState == OrderState.PROCESSED) {
            currentState = OrderState.SHIPPED;
        } else if (currentState == OrderState.SHIPPED) {
            currentState = OrderState.DELIVERED;
        }
        System.out.println("Order state updated to: " + currentState);
    }

    public void cancel() {
        if (currentState == OrderState.DELIVERED || currentState == OrderState.CANCELED) {
            throw new CannotCancelFinalOrderException();
        }
        history.push(currentState);
        currentState = OrderState.CANCELED;
        System.out.println("Order has been canceled.");
    }

    public void undoState() {
        if (history.isEmpty()) {
            throw new CannotRevertInitialOrderStateException();
        }
        currentState = history.pop();
        System.out.println("Order state reverted to: " + currentState);
    }
}
