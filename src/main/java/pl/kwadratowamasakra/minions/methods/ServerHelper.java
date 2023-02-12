package pl.kwadratowamasakra.minions.methods;

import java.util.ArrayList;
import java.util.List;

public class ServerHelper {

    private final List<Minion> minionList = new ArrayList<>();

    public final List<Minion> getMinions() {
        return minionList;
    }

    public final void addMinion(final Minion minion) {
        minionList.add(minion);
    }

    public final void removeMinion(final Minion minion) {
        minionList.remove(minion);
    }
}
