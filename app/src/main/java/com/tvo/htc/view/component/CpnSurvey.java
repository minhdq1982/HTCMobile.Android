package com.tvo.htc.view.component;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.HtmlCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.lib.util.LibUtils;
import com.android.lib.util.Logger;
import com.google.gson.internal.LinkedTreeMap;
import com.tvo.htc.R;
import com.tvo.htc.model.AnswerSurvey;
import com.tvo.htc.model.Question;
import com.tvo.htc.util.Utils;
import com.tvo.htc.view.main.profile.car.add_car.listener.DateWatcher;
import com.tvo.htc.view.main.survey.adapter.CheckboxAdapter;
import com.tvo.htc.view.main.survey.adapter.LevelAdapter;
import com.tvo.htc.view.main.survey.adapter.RadioAdapter;
import com.tvo.htc.view.main.survey.listener.OnDataChangeListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.text.HtmlCompat.FROM_HTML_MODE_COMPACT;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class CpnSurvey extends FrameLayout {
    public enum Type {
        EMAIL(R.layout.item_survey_type_text),
        FULL_NAME(R.layout.item_survey_type_text),
        PHONE(R.layout.item_survey_type_text),
        TEXT(R.layout.item_survey_type_text),
        TEXT_AREA(R.layout.item_survey_type_text_area),
        SPINNER(R.layout.item_survey_type_sinpper),
        DATE(R.layout.item_survey_type_date),
        LEVEL(R.layout.item_survey_type_level),
        RADIO(R.layout.item_survey_type_radio),
        CHECKBOX(R.layout.item_survey_type_checkbox);
        public int layoutRes;

        Type(@LayoutRes int layoutRes) {
            this.layoutRes = layoutRes;
        }
    }

    private Type mType;
    private int mId;
    private CharSequence mQuestion;
    private CharSequence mDescription;
    private List<String> mAnswers;
    private List<String> mSubAnswer;
    private boolean mRequire;
    private boolean mMultiSelection = false;
    private int mSelection = -1;
    private int mInputType = InputType.TYPE_CLASS_TEXT;
    private OnDataChangeListener dataChangeListener;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.flContainer)
    FrameLayout flContainer;


    EditText editTextArea;

    EditText editText;

    Spinner spinner;

    EditText editMore;

    RadioAdapter mRadioAdapter;
    LevelAdapter mLevelAdapter;
    CheckboxAdapter mCheckboxAdapter;


    public CpnSurvey(Context context) {
        super(context);
        init(null, 0);
    }

    public CpnSurvey(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CpnSurvey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.cpn_survey, this, true);
        ButterKnife.bind(this);

//        if (attrs != null) {
//            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable
//                    .CpnSurvey, defStyleAttr, 0);
//            mType = Type.values()[a.getInt(R.styleable.CpnSurvey_type_survey, 0)];
//            mQuestion = a.getText(R.styleable.CpnSurvey_question);
//            mDescription = a.getText(R.styleable.CpnSurvey_description);
//            mId = a.getInt(R.styleable.CpnSurvey_id, -1);
//            mRequire = a.getBoolean(R.styleable.CpnSurvey_require, false);
//            int idArrayAnswer = a.getResourceId(R.styleable.CpnSurvey_answers, 0);
//            if (idArrayAnswer != 0) {
//                mAnswers = Arrays.asList(getContext().getResources().getStringArray(idArrayAnswer));
//            } else {
//                mAnswers = new ArrayList<>();
//            }
//            a.recycle();
//            updateView();
//        }
    }

    public void updateView() {
        if (mRequire) {
            tvQuestion.setText(HtmlCompat.fromHtml(mQuestion.toString() + "<font color=\"red\"> * </font>", FROM_HTML_MODE_COMPACT));
            tvQuestion.setTextColor(ContextCompat.getColor(getContext(), R.color.colorText));
        } else {
            tvQuestion.setText(mQuestion);
            tvQuestion.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextSub));
        }

        if (mDescription != null && !mDescription.toString().isEmpty()) {
            tvDescription.setText(mDescription);
            tvDescription.setVisibility(VISIBLE);
        } else {
            tvDescription.setVisibility(GONE);
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        flContainer.addView(inflater.inflate(mType.layoutRes, null));

        switch (mType) {
            case TEXT:
                editText = findViewById(R.id.editText);
                editText.setInputType(mInputType);
                break;
            case DATE:
                editText = findViewById(R.id.editText);
                editText.addTextChangedListener(new DateWatcher(editText));
                break;
            case PHONE:
                editText = findViewById(R.id.editText);
                editText.setInputType(mInputType);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                break;
            case EMAIL:
                editText = findViewById(R.id.editText);
                editText.setInputType(mInputType);
                break;
            case FULL_NAME:
                editText = findViewById(R.id.editText);
                editText.setInputType(mInputType);
                break;
            case TEXT_AREA:
                editTextArea = findViewById(R.id.editTextArea);
                break;
            case LEVEL:
                initLevelAdapter();
                break;
            case SPINNER:
                initSpinnerAdapter();
                break;
            case RADIO:
                initRadioAdapter();
                break;
            case CHECKBOX:
                initCheckboxAdapter();
                break;
        }
        listenerDataChanged();
    }

    public void listenerDataChanged() {
        if (editText != null) {
            editText.addTextChangedListener(new OnTextChange());
        }
        if (editTextArea != null) {
            editTextArea.addTextChangedListener(new OnTextChange());
        }
        if (editMore != null) {
            editMore.addTextChangedListener(new OnTextChange());
        }
    }

    public void updateTextData(String s) {
        if (s != null) {
            switch (mType) {
                case DATE:
                case TEXT:
                case PHONE:
                case FULL_NAME:
                case EMAIL:
                    editText.setText(s);
                    break;
                case TEXT_AREA:
                    editTextArea.setText(s);
                    break;
            }
        }
    }

    public void updatePrefData(AnswerSurvey answerSurvey) {
        if (answerSurvey.data != null) {
            switch (mType) {
                case TEXT:
                case DATE:
                case PHONE:
                case FULL_NAME:
                case EMAIL:
                    editText.setText(answerSurvey.data.toString());
                    break;
                case TEXT_AREA:
                    editTextArea.setText(answerSurvey.data.toString());
                    break;

                case CHECKBOX:
                    mCheckboxAdapter.setSelection((List<Integer>) answerSurvey.data);
                    break;

                case RADIO:
                    mRadioAdapter.changeSelect(castDouble(answerSurvey.data, -1));
                    break;
                case LEVEL:
                    Pair<Integer, Integer> pairLevel = castLinkTreeMap(answerSurvey.data, Pair.create(-1, 0));
                    mLevelAdapter.changeSelect(pairLevel.first);
                    if (pairLevel.second != -1 && mSubAnswer != null && !mSubAnswer.isEmpty()) {
                        RadioGroup rdSubAnswer = findViewById(R.id.rdSubAnswer);
                        RadioButton rdBtn = rdSubAnswer.findViewById(pairLevel.second);
                        if (rdBtn != null) {
                            rdBtn.setChecked(true);
                        }
                    }
                    break;
                case SPINNER:
                    spinner.setSelection(castDouble(answerSurvey.data, 0));
                    break;
            }
        }
    }

    public boolean hasDataRequire() {
        if (mRequire) {
            switch (mType) {
                case TEXT:
                    return !editText.getText().toString().isEmpty();
                case PHONE:
                    return Utils.isValidPhoneNumber(editText.getText().toString());
                case EMAIL:
                    return Utils.isValidEmail(editText.getText().toString());
                case FULL_NAME:
                    return Utils.isValidName(editText.getText().toString());
                case DATE:
                    String stringDate = editText.getText().toString();
                    if (stringDate.isEmpty()) {
                        return false;
                    } else {
                        return LibUtils.isValidTime(stringDate);
                    }
                case TEXT_AREA:
                    return !editTextArea.getText().toString().isEmpty();
                case RADIO:
                    return mRadioAdapter.hasSelected();
                case LEVEL:
                    return mLevelAdapter.hasSelected();
                case CHECKBOX:
                    return mCheckboxAdapter.hasSelected() || !editMore.getText().toString().isEmpty();
            }
        }
        return true;
    }


    public AnswerSurvey getData() {
        String answer = "";
        Object data = null;
        switch (mType) {
            case DATE:
            case TEXT:
            case PHONE:
            case EMAIL:
            case FULL_NAME:
                answer = editText.getText().toString();
                data = answer;
                break;
            case TEXT_AREA:
                answer = editTextArea.getText().toString();
                data = answer;
                break;
            case SPINNER:
                answer = spinner.getSelectedItem().toString();
                data = spinner.getSelectedItemPosition();
                break;
            case LEVEL:
                answer = mLevelAdapter.getAnswer();
                int positionLevel = mLevelAdapter.getSelectedPosition();
                int idRadioLevel = -1;
                if (mSubAnswer != null && !mSubAnswer.isEmpty()) {
                    answer += getCheckedStringRadioButton(findViewById(R.id.rdSubAnswer));
                    idRadioLevel = getCheckedIdRadioButton(findViewById(R.id.rdSubAnswer));
                }
                Logger.e("Level data: "+answer);
                data = Pair.create(positionLevel, idRadioLevel);
                break;
            case CHECKBOX:
                answer = mCheckboxAdapter.getAnswer();
                if (answer.isEmpty()) {
                    answer = editMore.getText().toString();
                }
                data = mCheckboxAdapter.getListSelection();
                break;
            case RADIO:
                answer = mRadioAdapter.getAnswer();
                data = mRadioAdapter.getSelectedPosition();
                break;
        }

        return new AnswerSurvey(mId, mQuestion.toString(), answer, data);
    }

    private void initLevelAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        RadioGroup rdSubAnswer = findViewById(R.id.rdSubAnswer);

        mLevelAdapter = new LevelAdapter(getContext(), Arrays.asList(getContext().getResources().getStringArray(R.array.survey_list_levels)));
        mLevelAdapter.setOnItemClickListener((adapter, view, position) -> {
            rdSubAnswer.clearCheck();
            mLevelAdapter.changeSelect(position);
            onDataChanged();
        });
        recyclerView.setAdapter(mLevelAdapter);

        if (mSubAnswer != null && !mSubAnswer.isEmpty()) {
            rdSubAnswer.setVisibility(VISIBLE);
            createRadioButton(rdSubAnswer, mSubAnswer, false);
            registerEventRadioGroup(rdSubAnswer, true);
        }
    }

    private void registerEventRadioGroup(RadioGroup radioGroup, boolean register) {
        if (register) {
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId != -1) {
                    mLevelAdapter.changeSelect(-1);
                    onDataChanged();
                }
            });
        } else {
            radioGroup.setOnCheckedChangeListener(null);
        }
    }

    private void initSpinnerAdapter() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mAnswers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onDataChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (mSelection != -1) {
            spinner.setSelection(mSelection);
        } else {
            onDataChanged();
        }
    }

    private void initRadioAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        mRadioAdapter = new RadioAdapter(getContext(), mAnswers, mSelection);
        mRadioAdapter.setOnItemClickListener((adapter, view, position) -> {
            mRadioAdapter.changeSelect(position);
            onDataChanged();
        });
        recyclerView.setAdapter(mRadioAdapter);
    }

    private void initCheckboxAdapter() {
        editMore = findViewById(R.id.editMore);
        CheckBox chkMore = findViewById(R.id.chkMore);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        mCheckboxAdapter = new CheckboxAdapter(getContext(), mAnswers, mMultiSelection);
        mCheckboxAdapter.setOnItemClickListener((adapter, view, position) -> {
            mCheckboxAdapter.changeSelect(position);
            chkMore.setChecked(false);
            onDataChanged();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mCheckboxAdapter);


        chkMore.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                mCheckboxAdapter.changeSelect(-1);
            }
        });
    }

    public void createViewQuestion(Question item) {
        setType(item.getType());
        setRequire(item.isRequire());
        setQuestion(item.getQuestion());
        setDescription(item.getDescription());
        setAnswers(item.getAnswers());
        setSelection(item.getSelection());
        setMultiSelection(item.isMultiSelection());
        setSubAnswer(item.getSubAnswer());
        setInputType(item.getInputType());
        updateView();
        updateTextData(item.getDataPref());
    }

    public void setInputType(int inputType) {
        this.mInputType = inputType;
    }

    public void setType(Type mType) {
        this.mType = mType;
    }

    public Type getType() {
        return mType;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setQuestion(CharSequence mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setDescription(CharSequence mDescription) {
        this.mDescription = mDescription;
    }

    public void setAnswers(List<String> mAnswers) {
        this.mAnswers = mAnswers;
    }

    public void setRequire(boolean mRequire) {
        this.mRequire = mRequire;
    }

    public void setSelection(int index) {
        this.mSelection = index;
    }

    public void setMultiSelection(boolean mutiSelection) {
        this.mMultiSelection = mutiSelection;
    }

    public void setSubAnswer(List<String> subAnswer) {
        this.mSubAnswer = subAnswer;
    }

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.dataChangeListener = onDataChangeListener;
    }


    private void onDataChanged() {
        if (dataChangeListener != null) {
            if (mRequire && hasDataRequire()) {
                dataChangeListener.onDataChanged();
            } else {
                dataChangeListener.onDataChanged();
            }
        }
    }

    private void createRadioButton(RadioGroup rdg, List<String> answer, boolean useMargin) {
        int paddingLeft = Utils.getDimensions(getContext(), R.dimen.margin_padding_1x);
        for (int i = 0; i < answer.size(); i++) {
            RadioButton button = new RadioButton(getContext());
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(-2, -2);
            if (i != answer.size() - 1)
                layoutParams.setMargins(0, 0, 0, Utils.getDimensions(getContext(), R.dimen.margin_padding_0_5x));
            button.setLayoutParams(layoutParams);
            button.setPadding(paddingLeft, 0, 0, 0);
            button.setId(i);
            button.setText(answer.get(i));
            button.setButtonDrawable(R.drawable.bg_radio);
            rdg.addView(button);
        }
    }

    private String getCheckedStringRadioButton(RadioGroup rdg) {
        int selectedId = rdg.getCheckedRadioButtonId();
        if (selectedId != -1) {
            return ((RadioButton) findViewById(selectedId)).getText().toString();
        }
        return "";
    }

    private int getCheckedIdRadioButton(RadioGroup rdg) {
        return rdg.getCheckedRadioButtonId();
    }

    private int castDouble(Object data, int defaultData) {
        if (data instanceof Integer) {
            return (Integer) data;
        } else if (data instanceof Double) {
            double temp = (Double) data;
            return (int) temp;
        }
        return defaultData;
    }

    private Pair<Integer, Integer> castLinkTreeMap(Object data, Pair<Integer, Integer> defaultData) {
        if (data instanceof Pair) {
            return (Pair<Integer, Integer>) data;
        } else if (data instanceof LinkedTreeMap) {
            LinkedTreeMap temp = (LinkedTreeMap) data;
            double first = temp.get("first") != null ? (Double) temp.get("first") : defaultData.first;
            double second = temp.get("second") != null ? (Double) temp.get("second") : defaultData.second;
            return Pair.create((int) first, (int) second);
        }
        return defaultData;
    }

    class OnTextChange implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s != null && !s.toString().isEmpty()) {
                onDataChanged();
            }
        }
    }
}
