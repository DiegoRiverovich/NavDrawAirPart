package com.example.andrey.navdrawairpart;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Andrey on 10.03.2018.
 */

public class MetricFragment extends Fragment {

    private int previousLength;
    private boolean backSpace;

    EditText mks;
    EditText mkm;
    EditText mkh;
    EditText ls;
    EditText lm;
    EditText lh;

    boolean ignoreChange = false;
    boolean dotIn = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_metric1, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mks = (EditText)view.findViewById(R.id.mksET);
        mkm = (EditText)view.findViewById(R.id.mkmET);
        mkh = (EditText)view.findViewById(R.id.mkhET);
        ls = (EditText)view.findViewById(R.id.lsET);
        lm = (EditText)view.findViewById(R.id.lmET);
        lh = (EditText)view.findViewById(R.id.lhET);

        /*Drawable x = getResources().getDrawable(R.drawable.clear1);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        mks.setCompoundDrawables(null, null, x, null);*/

        mks.addTextChangedListener(new TextWatcher() {

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

                        if (mks.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            mks.setText("");
                            ignoreChange = false;
                            return;
                        }



                        /*if (dotIn) {
                            ignoreChange = false;
                            return;
                        }

                        if (s.toString().contains(".")) {
                            dotIn = true;
                            Log.d("dotIn True", "onTextChanged: ");
                        }*/



                        //float nMkm = Float.valueOf(mks.getText().toString());
                        //String sMks = String.valueOf(Float.valueOf(mks.getText().toString()) * 59.988002);
                        try {
                            String sMkm = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 59.988002);
                            String sMkh = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 3599.280143);
                            String sls = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 1_000.000000);
                            String slm = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 59_988.002399);
                            String slh = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 3_599_280.143971);

                            mkm.setText(sMkm);
                            mkh.setText(sMkh);
                            ls.setText(sls);
                            lm.setText(slm);
                            lh.setText(slh);

                            ignoreChange = false;

                        } catch (NumberFormatException nfe) {
                            mks.setText("");
                            ignoreChange = false;
                            return;
                        }

                        //mks.setText(sMks);


                        ignoreChange = false;
                    }

                } else {
                    return;
                }

            }
        });

        mks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////


        mkm.addTextChangedListener(new TextWatcher() {

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

                        if (mkm.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            mkm.setText("");
                            ignoreChange = false;
                            return;
                        }


                        try {
                            String sMks = String.format("%.8f", Float.valueOf(mkm.getText().toString()) * 0.016670);
                            //String sMkm = String.format("%.6f", Float.valueOf(mks.getText().toString()) * 59.988002);
                            String sMkh = String.format("%.8f", Float.valueOf(mkm.getText().toString()) * 60.0);
                            String sls = String.format("%.8f", Float.valueOf(mkm.getText().toString()) * 16.67);
                            String slm = String.format("%.8f", Float.valueOf(mkm.getText().toString()) * 1_000.0);
                            String slh = String.format("%.8f", Float.valueOf(mkm.getText().toString()) * 60_000.0);

                            mks.setText(sMks);
                            //mkm.setText(sMkm);
                            mkh.setText(sMkh);
                            ls.setText(sls);
                            lm.setText(slm);
                            lh.setText(slh);

                            ignoreChange = false;
                        } catch (NumberFormatException nfe) {
                            mkm.setText("");
                            ignoreChange = false;
                            return;
                        }
                    }

                    //Toast.makeText(getActivity(), "MKM PRINT", Toast.LENGTH_SHORT).show();

                } else {
                    return;
                }
            }
        });

        mkm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        mkh.addTextChangedListener(new TextWatcher() {

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

                        if (mkh.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            mkh.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {
                            String sMks = String.format("%.8f", Float.valueOf(mkh.getText().toString()) * 0.000277);
                            String sMkm = String.format("%.8f", Float.valueOf(mkh.getText().toString()) * 0.016666);
                            //String sMkh = String.format("%.6f", Float.valueOf(mkh.getText().toString()) * 60.0);
                            String sls = String.format("%.8f", Float.valueOf(mkh.getText().toString()) * 0.2778);
                            String slm = String.format("%.8f", Float.valueOf(mkh.getText().toString()) * 16.67);
                            String slh = String.format("%.8f", Float.valueOf(mkh.getText().toString()) * 1000.0);

                            mks.setText(sMks);
                            mkm.setText(sMkm);
                            //mkh.setText(sMkh);
                            ls.setText(sls);
                            lm.setText(slm);
                            lh.setText(slh);
                        } catch (NumberFormatException nfe) {
                            mkh.setText("");
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

        mkh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        ls.addTextChangedListener(new TextWatcher() {

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


                        if (ls.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            ls.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {
                            String sMks = String.format("%.8f", Float.valueOf(ls.getText().toString()) * 0.001);
                            String sMkm = String.format("%.8f", Float.valueOf(ls.getText().toString()) * 0.06);
                            String sMkh = String.format("%.6f", Float.valueOf(ls.getText().toString()) * 3.6);
                            //String sls = String.format("%.8f", Float.valueOf(ls.getText().toString()) * 0.2778);
                            String slm = String.format("%.8f", Float.valueOf(ls.getText().toString()) * 60.0);
                            String slh = String.format("%.8f", Float.valueOf(ls.getText().toString()) * 3_600.0);

                            mks.setText(sMks);
                            mkm.setText(sMkm);
                            mkh.setText(sMkh);
                            //ls.setText(sls);
                            lm.setText(slm);
                            lh.setText(slh);

                        } catch (NumberFormatException nfe) {
                            ls.setText("");
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

        ls.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////


        lm.addTextChangedListener(new TextWatcher() {

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

                        if (lm.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            lm.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 0.00001667);
                            String sMkm = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 0.001);
                            String sMkh = String.format("%.6f", Float.valueOf(lm.getText().toString()) * 0.06);
                            String sls = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 0.01667);
                            //String slm = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 60.0);
                            String slh = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 60.0);

                            mks.setText(sMks);
                            mkm.setText(sMkm);
                            mkh.setText(sMkh);
                            ls.setText(sls);
                            //lm.setText(slm);
                            lh.setText(slh);

                        } catch (NumberFormatException nfe) {
                            lm.setText("");
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

        lm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////


        lh.addTextChangedListener(new TextWatcher() {

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

                        if (lh.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            lh.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String sMks = String.format("%.8f", Float.valueOf(lh.getText().toString()) * 0.0000002778);
                            String sMkm = String.format("%.8f", Float.valueOf(lh.getText().toString()) * 0.00001667);
                            String sMkh = String.format("%.6f", Float.valueOf(lh.getText().toString()) * 0.001);
                            String sls = String.format("%.8f", Float.valueOf(lh.getText().toString()) * 0.0002778);
                            String slm = String.format("%.8f", Float.valueOf(lh.getText().toString()) * 0.01667);
                            //String slh = String.format("%.8f", Float.valueOf(lm.getText().toString()) * 60.0);

                            mks.setText(sMks);
                            mkm.setText(sMkm);
                            mkh.setText(sMkh);
                            ls.setText(sls);
                            lm.setText(slm);
                            //lh.setText(slh);

                        } catch (NumberFormatException nfe) {
                            lh.setText("");
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

        lh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
