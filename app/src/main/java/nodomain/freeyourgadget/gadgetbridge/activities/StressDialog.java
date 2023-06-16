package nodomain.freeyourgadget.gadgetbridge.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

import nodomain.freeyourgadget.gadgetbridge.R;
import nodomain.freeyourgadget.gadgetbridge.model.ActivitySample;
import nodomain.freeyourgadget.gadgetbridge.model.DeviceService;

public class StressDialog extends Dialog {
    protected static final Logger LOG = LoggerFactory.getLogger(HeartRateDialog.class);
    LinearLayout stress_dialog_results_layout;
    RelativeLayout stress_dialog_loading_layout;

    TextView stress_widget_hr_value;
    TextView stress_widget_spo2_value;
    TextView stress_widget_pressure_value;

    LinearLayout stress_hr;
    LinearLayout stress_spo2;
    LinearLayout stress_pressure;

    TextView stress_dialog_label;

    final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case DeviceService.ACTION_REALTIME_SAMPLES:
                    setMeasurementResults(intent.getSerializableExtra(DeviceService.EXTRA_REALTIME_SAMPLE));
                    break;
                default:
                    LOG.info("ignoring intent action " + intent.getAction());
                    break;
            }
        }
    };

    public StressDialog(@NonNull Context context) {
        super(context);
    }

    private void setMeasurementResults(Serializable result) {
        stress_dialog_results_layout.setVisibility(View.VISIBLE);
        stress_dialog_loading_layout.setVisibility(View.GONE);
        stress_dialog_label.setText(getContext().getString(R.string.heart_rate_result));

        if (result instanceof ActivitySample) {
            ActivitySample sample = (ActivitySample) result;
            stress_hr.setVisibility(View.VISIBLE);
            if (HeartRateUtils.getInstance().isValidHeartRateValue(sample.getHeartRate()))
                stress_widget_hr_value.setText(String.valueOf(sample.getHeartRate()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DeviceService.ACTION_REALTIME_SAMPLES);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, filter);
        getContext().registerReceiver(mReceiver, filter);

        setContentView(R.layout.stress_dialog);
        stress_dialog_results_layout = findViewById(R.id.stress_dialog_results_layout);
        stress_dialog_loading_layout = findViewById(R.id.stress_dialog_loading_layout);

        stress_hr = findViewById(R.id.stress_measurements1);
        stress_spo2 = findViewById(R.id.stress_measurements2);
        stress_pressure = findViewById(R.id.stress_measurements3);

        TextView stress_widget_hr_title = stress_hr.findViewById(R.id.generic_widget_title);
        TextView stress_widget_spo2_title = stress_spo2.findViewById(R.id.generic_widget_title);
        TextView stress_widget_pressure_title = stress_pressure.findViewById(R.id.generic_widget_title);

        stress_widget_hr_value = stress_hr.findViewById(R.id.generic_widget_value);
        stress_widget_spo2_value = stress_spo2.findViewById(R.id.generic_widget_value);
        stress_widget_pressure_value = stress_pressure.findViewById(R.id.generic_widget_value);

        ImageView stress_widget_hr_icon = stress_hr.findViewById(R.id.generic_widget_icon);
        ImageView stress_widget_spo2_icon = stress_spo2.findViewById(R.id.generic_widget_icon);
        ImageView stress_widget_pressure_icon = stress_pressure.findViewById(R.id.generic_widget_icon);

        stress_widget_hr_icon.setImageResource(R.drawable.ic_heart);
        stress_widget_spo2_icon.setImageResource(R.drawable.ic_circle);
        stress_widget_pressure_icon.setImageResource(R.drawable.ic_heartrate);

        stress_hr.setVisibility(View.VISIBLE);
        stress_spo2.setVisibility(View.GONE);
        stress_pressure.setVisibility(View.GONE);

        stress_widget_hr_title.setText("Stress level");
        stress_widget_spo2_title.setText(R.string.menuitem_spo2);
        stress_widget_pressure_title.setText(R.string.blood_pressure);

        stress_dialog_label = findViewById(R.id.heart_rate_dialog_title);
        stress_dialog_results_layout.setVisibility(View.GONE);
        stress_dialog_loading_layout.setVisibility(View.VISIBLE);

        setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
                getContext().unregisterReceiver(mReceiver);
            }
        });
    }
}