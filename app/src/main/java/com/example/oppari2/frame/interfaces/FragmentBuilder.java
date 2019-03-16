package com.example.oppari2.frame.interfaces;

import com.example.oppari2.frame.canvas.BaseFragment;

public interface FragmentBuilder {
    BaseFragment getFragment(String fragmentId);
}
