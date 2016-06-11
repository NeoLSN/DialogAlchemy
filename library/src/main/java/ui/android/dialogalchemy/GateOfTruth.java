package ui.android.dialogalchemy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JasonYang on 2016/6/6.
 */
public final class GateOfTruth {

    private static class GateOfTruthHolder {
        private static final GateOfTruth INSTANCE = new GateOfTruth();
    }

    static GateOfTruth getInstance() {
        return GateOfTruthHolder.INSTANCE;
    }

    private final Map<String, Map<String, Object>> souls = new HashMap<String, Map<String, Object>>();

    private GateOfTruth() {
    }

    void save(String key, Map<String, Object> soul) {
        this.souls.put(key, soul);
    }

    Map<String, Object> withdraw(String key) {
        return souls.remove(key);
    }
}
