package com.example.andrey.navdrawairpart;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Andrey on 11.03.2018.
 */

public class PressureFragment extends Fragment {

    private int previousLength;
    private boolean backSpace;

    EditText atm;
    EditText kpa;
    EditText mpa;
    EditText psi;
    EditText kgs;
    EditText bar;

    boolean ignoreChange = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pressure, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        atm = (EditText)view.findViewById(R.id.atmET);
        kpa = (EditText)view.findViewById(R.id.kpaET);
        mpa = (EditText)view.findViewById(R.id.mpaET);
        psi = (EditText)view.findViewById(R.id.psiET);
        kgs = (EditText)view.findViewById(R.id.kgsET);
        bar = (EditText)view.findViewById(R.id.barET);

        /*Drawable x = getResources().getDrawable(R.drawable.clear1);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        mks.setCompoundDrawables(null, null, x, null);*/

        atm.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (atm.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            atm.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            //float nMkm = Float.valueOf(mks.getText().toString());
                            //String sMks = String.valueOf(Float.valueOf(mks.getText().toString()) * 59.988002);
                            String sMkm = String.format("%.6f", Float.valueOf(atm.getText().toString()) * 101.3);
                            String sMkh = String.format("%.6f", Float.valueOf(atm.getText().toString()) * 0.1013);
                            String sls = String.format("%.6f", Float.valueOf(atm.getText().toString()) * 14.7);
                            String slm = String.format("%.6f", Float.valueOf(atm.getText().toString()) * 1.033);
                            String slh = String.format("%.6f", Float.valueOf(atm.getText().toString()) * 1.013);

                            //mks.setText(sMks);
                            kpa.setText(sMkm);
                            mpa.setText(sMkh);
                            psi.setText(sls);
                            kgs.setText(slm);
                            bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            atm.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                } else {
                    return;
                }

            }
        });

        atm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////


        kpa.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (kpa.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            kpa.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(kpa.getText().toString()) * 0.009869);
                            //String sMkm = String.format("%.6f", Float.valueOf(kpa.getText().toString()) * 59.988002);
                            String sMkh = String.format("%.8f", Float.valueOf(kpa.getText().toString()) * 0.001);
                            String sls = String.format("%.8f", Float.valueOf(kpa.getText().toString()) * 0.145);
                            String slm = String.format("%.8f", Float.valueOf(kpa.getText().toString()) * 0.0102);
                            String slh = String.format("%.8f", Float.valueOf(kpa.getText().toString()) * 0.01);

                            atm.setText(sMks);
                            //kpa.setText(sMkm);
                            mpa.setText(sMkh);
                            psi.setText(sls);
                            kgs.setText(slm);
                            bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            kpa.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        kpa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        mpa.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (mpa.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            mpa.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(mpa.getText().toString()) * 9.869);
                            String sMkm = String.format("%.8f", Float.valueOf(mpa.getText().toString()) * 1_000.0);
                            //String sMkh = String.format("%.6f", Float.valueOf(mpa.getText().toString()) * 60.0);
                            String sls = String.format("%.8f", Float.valueOf(mpa.getText().toString()) * 145.0);
                            String slm = String.format("%.8f", Float.valueOf(mpa.getText().toString()) * 10.2);
                            String slh = String.format("%.8f", Float.valueOf(mpa.getText().toString()) * 10.0);

                            atm.setText(sMks);
                            kpa.setText(sMkm);
                            //mpa.setText(sMkh);
                            psi.setText(sls);
                            kgs.setText(slm);
                            bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            mpa.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        mpa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        psi.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (psi.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            psi.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(psi.getText().toString()) * 0.06805);
                            String sMkm = String.format("%.8f", Float.valueOf(psi.getText().toString()) * 6.89473);
                            String sMkh = String.format("%.6f", Float.valueOf(psi.getText().toString()) * 0.006895);
                            //String sls = String.format("%.8f", Float.valueOf(psi.getText().toString()) * 0.2778);
                            String slm = String.format("%.8f", Float.valueOf(psi.getText().toString()) * 0.07031);
                            String slh = String.format("%.8f", Float.valueOf(psi.getText().toString()) * 0.06895);

                            atm.setText(sMks);
                            kpa.setText(sMkm);
                            mpa.setText(sMkh);
                            //psi.setText(sls);
                            kgs.setText(slm);
                            bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            psi.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        psi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////


        kgs.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (kgs.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            kgs.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(kgs.getText().toString()) * 0.96785);
                            String sMkm = String.format("%.8f", Float.valueOf(kgs.getText().toString()) * 98.0672);
                            String sMkh = String.format("%.6f", Float.valueOf(kgs.getText().toString()) * 0.09807);
                            String sls = String.format("%.8f", Float.valueOf(kgs.getText().toString()) * 14.2235);
                            //String slm = String.format("%.8f", Float.valueOf(kgs.getText().toString()) * 60.0);
                            String slh = String.format("%.8f", Float.valueOf(kgs.getText().toString()) * 0.98067);

                            atm.setText(sMks);
                            kpa.setText(sMkm);
                            mpa.setText(sMkh);
                            psi.setText(sls);
                            //kgs.setText(slm);
                            bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            kgs.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        kgs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        bar.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                backSpace = previousLength > s.length();

                if (backSpace) {
                    onTextChanged(s, 0, 0, s.length());

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                previousLength = s.length();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (count != 0) {
                    if (!ignoreChange) {

                        ignoreChange = true;

                        if (bar.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            bar.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(bar.getText().toString()) * 0.98692);
                            String sMkm = String.format("%.8f", Float.valueOf(bar.getText().toString()) * 100.0);
                            String sMkh = String.format("%.6f", Float.valueOf(bar.getText().toString()) * 0.1);
                            String sls = String.format("%.8f", Float.valueOf(bar.getText().toString()) * 14.5038);
                            String slm = String.format("%.8f", Float.valueOf(bar.getText().toString()) * 1.01971);
                            //String slh = String.format("%.8f", Float.valueOf(bar.getText().toString()) * 60.0);

                            atm.setText(sMks);
                            kpa.setText(sMkm);
                            mpa.setText(sMkh);
                            psi.setText(sls);
                            kgs.setText(slm);
                            //bar.setText(slh);

                        } catch (NumberFormatException nfe) {
                            bar.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        bar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
