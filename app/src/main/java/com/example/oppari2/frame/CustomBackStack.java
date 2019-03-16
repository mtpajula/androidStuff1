package com.example.oppari2.frame;

import com.example.oppari2.frame.development.Dev;
import com.example.oppari2.frame.store.types.backstack.BackStep;

import java.util.ArrayList;

public class CustomBackStack {

    private static final String sender = "CustomBackStack";
    // Custom backstack array to handle back button action
    private ArrayList<BackStep> backStack;
    public String key;

    /**
     * TODO BackStack is broken. Refactor
     * @param key
     */

    public CustomBackStack(String key) {
        this.key = key;
        backStack = new ArrayList<>();
    }

    public void addToBackStack(int container, String fragment) {

        // Avoid repeating same fragment in backstack
        if (backStack.size() > 0) {
            if (backStack.get(backStack.size() - 1).getFragment().equals(fragment)) {
                Dev.m(sender, "Skip add. Last back step has same fragment name");
                return;
            }
        }

        // Avoid repeating same fragment in same container
        BackStep containerBackStep = null;
        for (BackStep  backStep : backStack) {
            if (backStep.getContainer() == container) {
                containerBackStep = backStep;
            }
        }

        if (containerBackStep != null) {
            if (containerBackStep.getFragment().equals(fragment)) {
                Dev.m(sender, "Skip add. Last back step in this container has same fragment name");
                return;
            }
        }

        // Fragment is allowed to enter backstack
        BackStep backStep = new BackStep();
        backStep.setContainer(container);
        backStep.setFragment(fragment);

        backStack.add(backStep);
    }

    public void removeLastFromBackStack() {
        Dev.m(sender, "removeLastFromBackStack");
        if (backStack.size() > 0) {
            backStack.remove(backStack.size() - 1);
        }
    }

    public String onBackPressed() {

        BackStep backFragment = null;
        // Custom backstack action
        // Latest fragment in backstack is current.
        // If more than two fragments in backstack, return to second last and remove last
        if (backStack.size() > 1) {
            backFragment = backStack.get(backStack.size() - 2);
            backStack.remove(backStack.size() - 1);

            if (skipLastInContainer(backFragment)) {
                Dev.m(sender, "skipLastInContainer is true");
                return onBackPressed();
            }
        }

        // If backstack has nothing. return null
        if (backFragment == null) {
            return null;
        }

        return backFragment.getFragment();
    }

    private boolean skipLastInContainer(BackStep backFragment) {

        for (BackStep backStep : backStack) {
            if (backFragment.getContainer() == backStep.getContainer()) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<BackStep> getBackStack() {
        return backStack;
    }

    public void setBackStack(ArrayList<BackStep> customBackStack) {
        this.backStack = customBackStack;
    }

    public String toString() {
        ArrayList<String> res = new ArrayList<>();

        for (BackStep backStep : backStack) {
            res.add("{ fragment:" + backStep.getFragment() + "}");
        }
        return res.toString();
    }
}
