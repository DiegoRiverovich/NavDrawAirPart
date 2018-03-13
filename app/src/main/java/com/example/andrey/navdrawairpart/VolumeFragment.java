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

public class VolumeFragment extends Fragment {


    private int previousLength;
    private boolean backSpace;

    EditText l;
    EditText gl;
    EditText dl;
    EditText ml;
    EditText km;
    EditText ks;
    EditText ky;
    EditText kf;
    EditText kd;
    EditText gal;

    boolean ignoreChange = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volume, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        l = (EditText)view.findViewById(R.id.lET);
        gl = (EditText)view.findViewById(R.id.glET);
        dl = (EditText)view.findViewById(R.id.dlET);
        ml = (EditText)view.findViewById(R.id.mlET);
        km = (EditText)view.findViewById(R.id.kmET);
        ks = (EditText)view.findViewById(R.id.ksET);
        ky = (EditText)view.findViewById(R.id.kyET);
        kf = (EditText)view.findViewById(R.id.kfET);
        kd = (EditText)view.findViewById(R.id.kdET);
        gal = (EditText)view.findViewById(R.id.galET);

        /*Drawable x = getResources().getDrawable(R.drawable.clear1);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        mks.setCompoundDrawables(null, null, x, null);*/

        l.addTextChangedListener(new TextWatcher() {

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

                        if (l.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            l.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {
                            //float nMkm = Float.valueOf(mks.getText().toString());
                            //String lv = String.valueOf(Float.valueOf(l.getText().toString()) * 59.988002);
                            String glv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.01);
                            String dlv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.1);
                            String mlv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 1_000.0);
                            String kmv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.001);
                            String ksv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 1_000);
                            String kyv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.00131);
                            String kfv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.03531);
                            String kdv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 61.024);
                            String galv = String.format("%.6f", Float.valueOf(l.getText().toString()) * 0.2642);

                            //l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            l.setText("");
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

        l.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////


        gl.addTextChangedListener(new TextWatcher() {

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

                        if (gl.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            gl.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 100);
                            //String glv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 59.988002);
                            String dlv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 10);
                            String mlv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 100_000);
                            String kmv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 0.1);
                            String ksv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 100_00);
                            String kyv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 0.13072);
                            String kfv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 3.531);
                            String kdv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 6102.4);
                            String galv = String.format("%.8f", Float.valueOf(gl.getText().toString()) * 26.4200);

                            l.setText(lv);
                            //gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            gl.setText("");
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

        gl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        dl.addTextChangedListener(new TextWatcher() {

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

                        if (dl.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            dl.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 10);
                            String glv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 0.1);
                            //String dlv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 0.001);
                            String mlv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 10_000);
                            String kmv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 0.01);
                            String ksv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 10_000);
                            String kyv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 0.01307);
                            String kfv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 0.3531);
                            String kdv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 610.24);
                            String galv = String.format("%.8f", Float.valueOf(dl.getText().toString()) * 2.642);

                            l.setText(lv);
                            gl.setText(glv);
                            //dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            dl.setText("");
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

        dl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////


        ml.addTextChangedListener(new TextWatcher() {

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

                        if (ml.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            ml.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.001);
                            String glv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.00001);
                            String dlv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.0001);
                            //String mlv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.145);
                            String kmv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.000001);
                            String ksv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 1);
                            String kyv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.000001);
                            String kfv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.00004);
                            String kdv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.06102);
                            String galv = String.format("%.8f", Float.valueOf(ml.getText().toString()) * 0.00026);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            //ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            ml.setText("");
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

        ml.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////


        km.addTextChangedListener(new TextWatcher() {

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

                        if (km.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            km.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 1_000);
                            String glv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 10);
                            String dlv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 100);
                            String mlv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 1_000_000);
                            //String kmv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 0.0102);
                            String ksv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 1_000_000);
                            String kyv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 1.3072);
                            String kfv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 35.31);
                            String kdv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 61024);
                            String galv = String.format("%.8f", Float.valueOf(km.getText().toString()) * 264.2);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            //km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            km.setText("");
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

        km.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        ks.addTextChangedListener(new TextWatcher() {

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

                        if (ks.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            ks.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.001);
                            String glv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.00001);
                            String dlv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.0001);
                            String mlv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 1);
                            String kmv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.000001);
                            //String ksv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.01);
                            String kyv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.000001308);
                            String kfv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.00004);
                            String kdv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.06102);
                            String galv = String.format("%.8f", Float.valueOf(ks.getText().toString()) * 0.00026);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            //ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            ks.setText("");
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

        ks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        ky.addTextChangedListener(new TextWatcher() {

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

                        if (ky.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            ky.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 764.994);
                            String glv = String.format("%.6f", Float.valueOf(ky.getText().toString()) * 7.64994);
                            String dlv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 76.4994);
                            String mlv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 764994);
                            String kmv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 0.76499);
                            String ksv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 764994);
                            //String kyv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 0.001);
                            String kfv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 27.0119);
                            String kdv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 46683.0);
                            String galv = String.format("%.8f", Float.valueOf(ky.getText().toString()) * 202.111);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            //ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            ky.setText("");
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

        ky.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        kf.addTextChangedListener(new TextWatcher() {

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

                        if (kf.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            kf.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 28.3206);
                            String glv = String.format("%.6f", Float.valueOf(kf.getText().toString()) * 0.28321);
                            String dlv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 2.83206);
                            String mlv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 28320.6);
                            String kmv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 0.02832);
                            String ksv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 28320.6);
                            String kyv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 0.03702);
                            //String kfv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 0.145);
                            String kdv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 1728.24);
                            String galv = String.format("%.8f", Float.valueOf(kf.getText().toString()) * 7.48230);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            //kf.setText(kfv);
                            kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            kf.setText("");
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

        kf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        kd.addTextChangedListener(new TextWatcher() {

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

                        if (kd.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            kd.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.01639);
                            String glv = String.format("%.6f", Float.valueOf(kd.getText().toString()) * 0.00016);
                            String dlv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.00164);
                            String mlv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 16.3870);
                            String kmv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.00002);
                            String ksv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 16.3870);
                            String kyv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.00002);
                            String kfv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.00058);
                            //String kdv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.0102);
                            String galv = String.format("%.8f", Float.valueOf(kd.getText().toString()) * 0.00433);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            //kd.setText(kdv);
                            gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            kd.setText("");
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

        kd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////



        gal.addTextChangedListener(new TextWatcher() {

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

                        if (gal.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            gal.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            String lv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 3.78501);
                            String glv = String.format("%.6f", Float.valueOf(gal.getText().toString()) * 0.03785);
                            String dlv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 0.37850);
                            String mlv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 3785.01);
                            String kmv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 0.00379);
                            String ksv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 3785.01);
                            String kyv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 0.00495);
                            String kfv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 0.13365);
                            String kdv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 230.977);
                            //String galv = String.format("%.8f", Float.valueOf(gal.getText().toString()) * 0.01);

                            l.setText(lv);
                            gl.setText(glv);
                            dl.setText(dlv);
                            ml.setText(mlv);
                            km.setText(kmv);
                            ks.setText(ksv);
                            ky.setText(kyv);
                            kf.setText(kfv);
                            kd.setText(kdv);
                            //gal.setText(galv);

                        } catch (NumberFormatException nfe) {
                            gal.setText("");
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

        gal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
