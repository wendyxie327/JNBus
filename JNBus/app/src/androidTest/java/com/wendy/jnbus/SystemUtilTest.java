package com.wendy.jnbus;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.wendy.jnbus.util.SystemUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wendy on 2017/1/10.
 */
@RunWith(AndroidJUnit4.class)
public class SystemUtilTest {

    @Test
    public void parseStr() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        SystemUtil.parseLineOperTime("济南大学:3月-10月 5:00-00:00  11月-2月 5:00-22:00 葛家庄:3月-10月5:00-00:00  11月-2月 5:00-22:00");
        SystemUtil.parseLineOperTime("公交驾校  : 6:00－20:30 解放桥东 : 6:00－21:05");
    }
}
