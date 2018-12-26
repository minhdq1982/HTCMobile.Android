package com.tvo.htc.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tvo.htc.R;
import com.tvo.htc.view.BaseActivity;
import com.tvo.htc.view.BaseFragment;


/**
 * Created by ThinhNH on 08/08/2016.
 */
public class FragmentUtil {

    /**
     * goto Fragment by class
     *
     * @param context
     * @param cls     class of fragment
     */
    public static void gotoFragment(Context context, Class<?> cls) {
        gotoFragment(context, cls, false);
    }

    /**
     * goto Fragment by class
     *
     * @param context
     * @param cls         class of fragment
     * @param isRefreshUI
     */
    public static void gotoFragment(Context context, Class<?> cls, boolean isRefreshUI) {
        if (context == null) {
            return;
        }

        try {
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() >= 0) {
                for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                    if (fragmentManager.getBackStackEntryAt(i).getName() != null &&
                            fragmentManager.getBackStackEntryAt(i).getName().equals(cls
                                    .getSimpleName())) {
                        if (isRefreshUI) {
                            onResumeFragment(context, cls);
                        }
                        return;
                    } else {
                        removeFragment(context, getCurrentFragment(context), false);
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * goto Fragment by tag
     *
     * @param context
     * @param tag
     */
    public static void gotoFragment(Context context, String tag) {
        if (context == null) {
            return;
        }
        try {
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() >= 0) {
                for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                    if (fragmentManager.getBackStackEntryAt(i).getName() != null &&
                            fragmentManager.getBackStackEntryAt(i).getName().equals(tag)) {

                        if (fragmentManager.findFragmentByTag(tag) != null) {
                            onResumeFragment(context, fragmentManager.findFragmentByTag(tag)
                                    .getClass());
                        }
                        return;
                    } else {
                        removeFragment(context, getCurrentFragment(context), false);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Check fragment is created
     *
     * @param context
     * @param cls     class is fragment need check
     * @return status after check
     */
    public static boolean hasFragment(Context context, Class<?> cls) {
        if (context == null) {
            return false;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 0) {
            for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                if (fragmentManager.getBackStackEntryAt(i).getName() != null && fragmentManager
                        .getBackStackEntryAt(i).getName().equals(cls.getSimpleName())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * check fragment created, it don't have last fragment
     *
     * @param context
     * @param cls     class is fragment need check
     * @return status after check
     */
    public static boolean hasPreviousFragment(Context context, Class<?> cls) {
        if (context == null) {
            return false;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 0) {
            for (int i = fragmentManager.getBackStackEntryCount() - 2; i >= 0; i--) {
                if (fragmentManager.getBackStackEntryAt(i).getName() != null && fragmentManager
                        .getBackStackEntryAt(i).getName().equals(cls.getSimpleName())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * s
     * find fragment
     *
     * @param context
     */
    public static Fragment getCurrentFragment(Context context) {
        if (context == null) {
            return null;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

        Fragment fragment = null;
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            fragment = fragmentManager.findFragmentByTag(fragmentManager.
                    getBackStackEntryAt(count - 1).getName());
        }

//        fragment = fragmentManager.findFragmentById(R.id.homeMainContainer);
//        if (fragment == null) {
//            fragment = fragmentManager.findFragmentById(R.id.mainContainer);
//        }

        return fragment;
    }

    /**
     * find fragment
     *
     * @param context
     */
    public static Fragment getPreviousFragment(Context context) {
        if (context == null) {
            return null;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

        int count = fragmentManager.getBackStackEntryCount();

        if (count > 1) {
            return fragmentManager.findFragmentByTag(fragmentManager.
                    getBackStackEntryAt(count - 2).getName());
        }

        return null;
    }

    /**
     * s
     * find fragment
     *
     * @param context
     */
    private static Fragment getFragment(Context context, int index) {
        if (context == null) {
            return null;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

        Fragment fragment = null;
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0 && index < count) {
            fragment = fragmentManager.findFragmentByTag(fragmentManager.
                    getBackStackEntryAt(index).getName());
        }

//        fragment = fragmentManager.findFragmentById(R.id.homeMainContainer);
//        if (fragment == null) {
//            fragment = fragmentManager.findFragmentById(R.id.mainContainer);
//        }

        return fragment;
    }

    /**
     * find fragment
     *
     * @param context
     * @param cls     class is fragment need check
     */
    public static Fragment findFragment(Context context, Class<?> cls) {
        if (context == null) {
            return null;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

        return fragmentManager.findFragmentByTag(cls.getSimpleName());
    }

    /**
     * check fragment is active
     *
     * @param context
     * @param cls
     * @return
     */
    public static boolean isActiveFragment(Context context, Class<?> cls) {
        if (context == null) {
            return false;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();

        if (count <= 0) {
            return false;
        }
        if (fragmentManager.getBackStackEntryAt(count - 1) != null && fragmentManager
                .getBackStackEntryAt(count - 1).getName() != null && fragmentManager
                .getBackStackEntryAt(count - 1).getName().equals(cls.getSimpleName())) {
            return true;
        }

        return false;
    }

    /**
     * clear all back stack
     *
     * @param context
     */
    public static void clearAllBackStack(Context context) {
        if (context == null) {
            return;
        }

        try {
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() >= 0) {
                for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                    removeFragment(context, getFragment(context, i), false);
                }
            }

            hideKeyboard(context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * replace fragment (screen)
     *
     * @param fragment
     * @param tag
     */
    public static void replaceFragment(Context context, Fragment fragment, String tag) {
        FragmentUtil.clearAllBackStack(context);
        replaceFragment(context, fragment, tag, R.id.mainContainer);
    }


    /**
     * replace fragment (screen) don't have tabbar
     *
     * @param fragment
     * @param tag
     */
    public static void replaceFragmentNoTabbar(Context context, Fragment fragment, String tag) {
        replaceFragment(context, fragment, tag, R.id.mainContainerNoTabbar);
    }

    /**
     * replace fragment (screen)
     *
     * @param context
     * @param fragment
     * @param tag
     * @param layoutId layout container
     */
    public static void replaceFragment(Context context, Fragment fragment, String tag, int layoutId) {
        if ((context == null) || (fragment == null)) {
            return;
        }

        try {
            if (tag == null) {
                tag = fragment.getClass().getSimpleName();
            }
            // is tablet
//			if (UiUtils.isTablet(context)) {
//				((FragmentActivity) context)
//						.getSupportFragmentManager()
//						.beginTransaction()
//						.setCustomAnimations(R.anim.fragment_slide_in,
//								R.anim.fragment_slide_out,
//								R.anim.fragment_slide_in,
//								R.anim.fragment_slide_out)
//						.replace(R.id.select_song_container, fragment, tag)
//						.commit();
//			}
//			// is mobile
//			else {
            Fragment currentFragment = FragmentUtil.getCurrentFragment(context);
            if (currentFragment != null && currentFragment.getClass().getSimpleName().equals(tag)) {
                return;
            }
            if (((FragmentActivity) context) instanceof BaseActivity) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(layoutId, fragment, tag).commitAllowingStateLoss();
            }

            hideKeyboard(context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start new fragment (screen)
     *
     * @param context
     * @param fragment
     * @param tag
     */
    public static void startFragment(Context context, Fragment fragment, String tag) {
        startFragment(context, fragment, tag, R.id.mainContainer);
    }

    /**
     * Start new fragment (screen)
     *
     * @param context
     * @param fragment
     * @param tag
     */
    public static void startFragmentNoTabbar(Context context, Fragment fragment, String tag) {
        startFragment(context, fragment, tag, R.id.mainContainerNoTabbar);
    }


    /**
     * Start new fragment (screen)
     *
     * @param context
     * @param fragment
     * @param tag
     * @param layoutId layout container
     */
    public static void startFragment(Context context, Fragment fragment, String tag, int layoutId) {
        if ((context == null) || (fragment == null)) {
            return;
        }


        try {
            if (tag == null) {
                tag = fragment.getClass().getSimpleName();
            }
            // is tablet
//			if (UiUtils.isTablet(context)) {
//				((FragmentActivity) context)
//						.getSupportFragmentManager()
//						.beginTransaction()
//						.setCustomAnimations(R.anim.fragment_slide_in,
//								R.anim.fragment_slide_out,
//								R.anim.fragment_slide_in,
//								R.anim.fragment_slide_out)
//						.add(R.id.select_song_container, fragment, tag)
//						.addToBackStack(tag).commitAllowingStateLoss();
//				// .commit();
//			}
//			// is mobile
//			else {
            Fragment currentFragment = FragmentUtil.getCurrentFragment(context);
            if (currentFragment != null && currentFragment.getClass().getSimpleName().equals(tag)) {
                return;
            }
            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            if (((FragmentActivity) context) instanceof BaseActivity) {
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in,
                        R.anim.fragment_slide_out, R.anim.fragment_slide_in, R.anim
                                .fragment_slide_out).add(layoutId, fragment, tag)
                        .addToBackStack(tag).commitAllowingStateLoss();
            }

            hideKeyboard(context);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * remove fragment
     *
     * @param context
     */
    public static void removeFragment(Context context) {
        if (context == null) {
            return;
        }

        try {
            hideKeyboard(context);

            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                if (getPreviousFragment(context) != null) {
                    onResumeFragment(context, getPreviousFragment(context).getClass());
                }
                removeFragment(context, getCurrentFragment(context));
                return;
            } else {
                //TODO exit app
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * remove fragment
     *
     * @param context
     * @param fragment
     */
    public static void removeFragment(Context context, Fragment fragment) {
        removeFragment(context, fragment, true);
    }

    /**
     * remove fragment
     *
     * @param context
     * @param fragment
     */
    public static void removeFragment(Context context, Fragment fragment, boolean hasAnimation) {
        if ((context == null) || (fragment == null)) {
            return;
        }

        try {
            hideKeyboard(context);

            FragmentManager fragmentManager = ((FragmentActivity) context)
                    .getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() >= 0) {
                for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                    if (fragmentManager.getBackStackEntryAt(i).getName() != null &&
                            fragmentManager.getBackStackEntryAt(i).getName().equals(fragment
                                    .getClass().getSimpleName())) {
                        if (getPreviousFragment(context) != null) {
                            onResumeFragment(context, getPreviousFragment(context).getClass());
                        }

                        if (hasAnimation) {
                            fragmentManager.popBackStack();
                            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                        } else {
                            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                            fragmentManager.popBackStack();
//                            fragmentManager.popBackStack(fragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        }

                        return;
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private static void onResumeFragment(Context context, Class<?> cls) {
        if (cls == null) {
            return;
        }
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        Fragment visibleFragment = fragmentManager.findFragmentByTag(cls.getSimpleName());
        if (visibleFragment != null && visibleFragment instanceof BaseFragment) {
            ((BaseFragment) visibleFragment).onResumeFragment();
        }
    }

    /**
     * Hidden Keyboard
     *
     * @param context
     */
    public static void showKeyboard(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(FragmentActivity
                .INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Hidden Keyboard
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        if (context == null) {
            return;
        }
        // Check if no view has focus:
        View view = ((FragmentActivity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService
                    (Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                    .RESULT_UNCHANGED_SHOWN);
        }
    }
}
