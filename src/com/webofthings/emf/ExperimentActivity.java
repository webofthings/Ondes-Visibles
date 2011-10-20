package com.webofthings.emf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.webofthings.emf.experiments.ExperimentInfo;
import com.webofthings.emf.utils.Params;

public class ExperimentActivity extends Activity {
	private Params params;
	private ExperimentInfo currentExperiment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.experiment_details);
	}

	private void loadExperimentData() {
		currentExperiment = new ExperimentInfo();
		int deviceId = params.getDeviceId();
		if (deviceId != -1) {
			if (params.isLF()) {
				currentExperiment.setInstructions(getResources()
						.getStringArray(R.array.exp_lf_instructions)[deviceId]);
				currentExperiment.setConclusion(getResources().getStringArray(
						R.array.exp_lf_conclusions)[deviceId]);
			} else {
				currentExperiment.setInstructions(getResources()
						.getStringArray(R.array.exp_hf_instructions)[deviceId]);
				currentExperiment.setConclusion(getResources().getStringArray(
						R.array.exp_hf_conclusions)[deviceId]);
			}
		} else {
			currentExperiment.setInstructions(getResources().getString(
					R.string.no_device_selected));
			currentExperiment.setConclusion(getResources().getString(
					R.string.no_device_selected));
		}
	}

	protected void onResume() {
		super.onResume();
		params = new Params(getIntent(), this);
		loadExperimentData();

		TextView tv = (TextView) findViewById(R.id.experiment_details_textview);
		if (params.isConclusion()) {
			setTitle(getString(R.string.experiment_activity_title_conclusion));
			tv.setText(currentExperiment.getConclusion());
		} else {
			setTitle(getString(R.string.experiment_activity_title_instructions));
			tv.setText(currentExperiment.getInstructions());
		}
	}
}