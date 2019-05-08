package edu.aku.hassannaqvi.fas.ui.tool1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.fas.JSON.GeneratorClass;
import edu.aku.hassannaqvi.fas.R;
import edu.aku.hassannaqvi.fas.RMOperations.crudOperations;
import edu.aku.hassannaqvi.fas.core.CONSTANTS;
import edu.aku.hassannaqvi.fas.core.MainApp;
import edu.aku.hassannaqvi.fas.data.DAO.FormsDAO;
import edu.aku.hassannaqvi.fas.data.entities.Forms;
import edu.aku.hassannaqvi.fas.databinding.ActivitySectionD04Binding;
import edu.aku.hassannaqvi.fas.ui.EndingActivity;
import edu.aku.hassannaqvi.fas.utils.JsonUtils;
import edu.aku.hassannaqvi.fas.validation.ValidatorClass;

public class SectionD04Activity extends AppCompatActivity {

    private ActivitySectionD04Binding bi;
    private Forms fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d04);
        bi.setCallback(this);

        setContentUI();
    }

    private void setContentUI() {
        this.setTitle(R.string.hfa14);
        fc = (Forms) getIntent().getSerializableExtra(CONSTANTS._URI_FC_OBJ);

    }

    public void onRadioClickChanged(RadioGroup radioGroup, int id) {
        if (radioGroup.getCheckedRadioButtonId() == bi.hfa1444avc.getId())
            bi.hfa1444bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1445avc.getId())
            bi.hfa1445bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1446avc.getId())
            bi.hfa1446bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1447avc.getId())
            bi.hfa1447bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1448avc.getId())
            bi.hfa1448bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1449avc.getId())
            bi.hfa1449bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1450avc.getId())
            bi.hfa1450bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1451avc.getId())
            bi.hfa1451bf.clearCheck();
        else if (radioGroup.getCheckedRadioButtonId() == bi.hfa1452avc.getId())
            bi.hfa1452bf.clearCheck();
    }


    public void BtnContinue() {

        if (!formValidation())
            return;

        SaveDraft();
        if (UpdateDB()) {
            MainApp.stActivity(this, this, SectionD05Activity.class, fc);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {
        try {

            Long longID = new crudOperations(this, FormsDAO.class.getName(), "formsDao", "updateForm", fc).execute().get();
            return longID == 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void SaveDraft() {
        JSONObject Json4 = GeneratorClass.getContainerJSON(bi.fldGrpllSecD04, true);

        try {
            JSONObject s4_merge = JsonUtils.mergeJSONObjects(new JSONObject(fc.getSa4()), Json4);

            fc.setSa4(String.valueOf(s4_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpllSecD04);
    }

    public void BtnEnd() {
        MainApp.endActivityDirectRouting(this, this, EndingActivity.class, false, fc);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
