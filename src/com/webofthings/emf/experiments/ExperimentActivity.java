package com.webofthings.emf.experiments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.webofthings.emf.R;
import com.webofthings.emf.R.array;
import com.webofthings.emf.R.id;
import com.webofthings.emf.R.layout;
import com.webofthings.emf.R.string;
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
		String instructions;
		String conclusions;
		
		if (params.isLF()) {
			if (params.isDevice()) {
				instructions = getResources().getStringArray(R.array.exp_lf_instructions)[deviceId];
				conclusions = getResources().getStringArray(R.array.exp_lf_conclusions)[deviceId];
			} else {
				instructions = getResources().getString(R.string.no_device_selected_lf);
				conclusions = instructions;
			}
		} else {
			if (params.isDevice()) {
				instructions = getResources().getStringArray(R.array.exp_hf_instructions)[deviceId];
				conclusions = getResources().getStringArray(R.array.exp_hf_conclusions)[deviceId];
			} else {
				instructions = getResources().getString(R.string.no_device_selected_hf);
				conclusions = instructions;
			}
		}
		currentExperiment.setInstructions(instructions);
		currentExperiment.setConclusion(conclusions);
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