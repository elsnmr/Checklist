package com.elsa.checklist.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.elsa.checklist.adapter.ClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * digunakan untuk fungsi yang di-reuse secara global
 */

public final class util {

    private static final String TAG = "CommonUtils";

    private util() {
        // This utility class is not publicly instantiable
    }

    public static int integer(Object object) {
        if (object instanceof Integer) {
            return (int) object;
        } else {
            return 0;
        }
    }

    public static String string(Object object) {
        if (object instanceof String) {
            return (String) object;
        } else {
            return null;
        }
    }

    public static boolean bool(Object object) {
        if (object instanceof Boolean) {
            return (boolean) object;
        } else {
            return false;
        }
    }

    public static double doubleVal(Object object) {
        if (object instanceof Double) {
            return (double) object;
        } else {
            return 0.0;
        }
    }

    public static ArrayList<Map<String, Object>> arrayList(Object object) {
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        if (object instanceof ArrayList) {
            ArrayList<?> list = (ArrayList<?>) object;
            for (Object objectItem : list) {
                data.add(map(objectItem));
            }
        }
        return data;
    }

    public static Map<String, Object> map(Object object) {
        Map<String, Object> result = new HashMap<>();
        if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) (object);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                Object value = entry.getValue();
                result.put(key, value);
            }
        }
        return result;
    }

    public static ClickListener listener(Object object) {
        if (object instanceof ClickListener) {
            return (ClickListener) object;
        } else {
            return null;
        }
    }

    /*
     * add image into image view from object drawable value
     * */
    public static void image(ImageView imageView, Object object, Context context) {
        try {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, util.integer(object)));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * get value from jsonobject
     * */
    public static Map<String, Object> jsonObjectToMapObj(JSONObject jsonObject) throws JSONException {
        Map<String, Object> mapData = new HashMap<>();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            // loop to get the dynamic key
            String currentDynamicKey = (String) keys.next();
            // get the value of the dynamic key
            Object currentDynamicValue = jsonObject.get(currentDynamicKey);
            // do something here with the value...
            mapData.put(currentDynamicKey, currentDynamicValue);
        }
        return mapData;
    }

    /*
     * keyboard function
     * */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftInput(EditText edit, Context context) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edit, 0);
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /*
     * menampilkan pesan informasi
     * */
    public static void showMessageDialog(Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
    }

    public static int ifNotNumeric(String s) {
        if (TextUtils.isDigitsOnly(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String toRupiahFormat(String s) {
        try {
            int d = ifNotNumeric(s);
            switch (s.length() / 3) {
                case 0:
                case 1:
                    return (d / 1000) + " Rb";
                case 2:
                    if (s.length() % 3 == 0) {
                        return (d / 1000) + " Rb";
                    } else {
                        return (d / 1000000) + " Jt";
                    }
                case 4:
                    if (s.length() % 3 == 0) {
                        return (d / 1000000) + " Juta";
                    } else {
                        return (d / 1000000000) + " Miliar";
                    }
                case 5:
                    if (s.length() % 3 == 0) {
                        return (d / 1000000000) + " Miliar";
                    } else {
                        return (d / 1000000000) + " Triliun";
                    }
                default:
                    return "";
            }
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public static String toRupiah(int balance, String currency) {
        DecimalFormat decimalFormat = new DecimalFormat(currency + "###,###,###", new DecimalFormatSymbols(Locale.getDefault()));
        return decimalFormat.format(balance);
    }
}
