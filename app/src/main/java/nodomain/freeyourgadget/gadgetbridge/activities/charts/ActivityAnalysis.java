package nodomain.freeyourgadget.gadgetbridge.activities.charts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import nodomain.freeyourgadget.gadgetbridge.model.ActivityAmount;
import nodomain.freeyourgadget.gadgetbridge.model.ActivityAmounts;
import nodomain.freeyourgadget.gadgetbridge.model.ActivityKind;
import nodomain.freeyourgadget.gadgetbridge.model.ActivitySample;

public class ActivityAnalysis {
    public static final Logger LOG = LoggerFactory.getLogger(ActivityAnalysis.class);

    // store raw steps and duration
    protected HashMap<Integer, Long> stats = new HashMap<Integer, Long>();
    // max speed determined from samples
    private int maxSpeed = 0;

    public boolean isWorn = true;

    public ActivityAmounts calculateActivityAmounts(List<? extends ActivitySample> samples) {
        ActivityAmount deepSleep = new ActivityAmount(ActivityKind.TYPE_DEEP_SLEEP);
        ActivityAmount lightSleep = new ActivityAmount(ActivityKind.TYPE_LIGHT_SLEEP);
        ActivityAmount remSleep = new ActivityAmount(ActivityKind.TYPE_REM_SLEEP);
        ActivityAmount notWorn = new ActivityAmount(ActivityKind.TYPE_NOT_WORN);
        ActivityAmount activity = new ActivityAmount(ActivityKind.TYPE_ACTIVITY);

        ActivityAmount previousAmount = null;
        ActivitySample previousSample = null;
        for (ActivitySample sample : samples) {
            ActivityAmount amount;
            isWorn = true;
            switch (sample.getKind()) {
                case ActivityKind.TYPE_DEEP_SLEEP:
                    amount = deepSleep;
                    break;
                case ActivityKind.TYPE_LIGHT_SLEEP:
                    amount = lightSleep;
                    break;
                case ActivityKind.TYPE_REM_SLEEP:
                    amount = remSleep;
                    break;
                case ActivityKind.TYPE_NOT_WORN:
                    amount = notWorn;
                    notifyDeviceNotWorn();
                    break;
                case ActivityKind.TYPE_ACTIVITY:
                default:
                    amount = activity;
                    break;
            }

            int steps = sample.getSteps();
            if (steps > 0) {
                amount.addSteps(steps);
            }

            if (previousSample != null) {
                long timeDifference = sample.getTimestamp() - previousSample.getTimestamp();
                if (previousSample.getRawKind() == sample.getRawKind()) {
                    amount.addSeconds(timeDifference);
                } else {
                    long sharedTimeDifference = (long) (timeDifference / 2.0f);
                    previousAmount.addSeconds(sharedTimeDifference);
                    amount.addSeconds(sharedTimeDifference);
                }

                // add time
                if (steps > 0 && sample.getKind() == ActivityKind.TYPE_ACTIVITY) {
                    if (steps > maxSpeed) {
                        maxSpeed = steps;
                    }

                    if (!stats.containsKey(steps)) {
//                        LOG.debug("Adding: " + steps);
                        stats.put(steps, timeDifference);
                    } else {
                        long time = stats.get(steps);
//                        LOG.debug("Updating: " + steps + " " + timeDifference + time);
                        stats.put(steps, timeDifference + time);
                    }
                }
            }

            amount.setStartDate(sample.getTimestamp());
            amount.setEndDate(sample.getTimestamp());

            previousAmount = amount;
            previousSample = sample;
        }

        ActivityAmounts result = new ActivityAmounts();
        if (deepSleep.getTotalSeconds() > 0) {
            result.addAmount(deepSleep);
        }
        if (lightSleep.getTotalSeconds() > 0) {
            result.addAmount(lightSleep);
        }
        if (remSleep.getTotalSeconds() > 0) {
            result.addAmount(remSleep);
        }
        if (activity.getTotalSeconds() > 0) {
            result.addAmount(activity);
        }
        if (notWorn.getTotalSeconds() > 0) {
            result.addAmount(notWorn);
        }

        result.calculatePercentages();

        return result;
    }

    private void notifyDeviceNotWorn() {
        LOG.debug("The device is not worn: ACTIVITY ANALYSIS");

        isWorn = false;
    }

    int calculateTotalSteps(List<? extends ActivitySample> samples) {
        int totalSteps = 0;
        for (ActivitySample sample : samples) {
            int steps = sample.getSteps();
            if (steps > 0) {
                totalSteps += steps;
            }
        }
        return totalSteps;
    }
}
