package com.example.andrey.navdrawairpart;

import android.app.Activity;
import android.app.Dialog;
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

import static java.lang.Math.pow;

/**
 * Created by Andrey on 12.03.2018.
 */

public class TubeDiameterCalcFragment extends Fragment {


    private int previousLength;
    private boolean backSpace;

    EditText cavmm;
    EditText cavms;
    EditText tl;
    EditText pd;
    EditText sdp;
    EditText idtm;
    EditText idtms;

    boolean ignoreChange = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tude_diameter_calc, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        cavmm = (EditText)view.findViewById(R.id.cavmmET);
        cavms = (EditText)view.findViewById(R.id.cavmsET);
        tl = (EditText)view.findViewById(R.id.tlET);
        pd = (EditText)view.findViewById(R.id.pdET);
        sdp = (EditText)view.findViewById(R.id.sdpET);
        idtm = (EditText)view.findViewById(R.id.idtmET);
        idtms = (EditText)view.findViewById(R.id.idtmsET);

        /*Drawable x = getResources().getDrawable(R.drawable.clear1);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        mks.setCompoundDrawables(null, null, x, null);*//*
        */

        cavmm.addTextChangedListener(new TextWatcher() {

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

                        //String scavmm = String.valueOf(Float.valueOf(cavmm.getText().toString()) * 59.988002);
                        //Log.d("lenght", "onTextChanged:" + tl.getText().toString() + "a" + pd.getText().toString() + "f" + sdp.getText().toString() + "!!");
                        if (cavmm.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            cavmm.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {
                            String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                            cavms.setText(scavms);
                            if (cavms.getText().toString().trim().length() > 0 && tl.getText().toString().trim().length() > 0 && pd.getText().toString().trim().length() > 0 && sdp.getText().toString().trim().length() > 0 ) {

                                //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);

                                String stl = String.format("%.6f", Float.valueOf(tl.getText().toString().replaceFirst("[.]", "")));
                                String spd = String.format("%.6f", Float.valueOf(pd.getText().toString().replaceFirst("[.]", "")));
                                String ssdp = String.format("%.6f", Float.valueOf(sdp.getText().toString().replaceFirst("[.]", "")));

                                scavms = scavms.replace(",", ".");
                                stl = stl.replace(",", ".");
                                spd = spd.replace(",", ".");
                                ssdp = ssdp.replace(",", ".");

                                Log.d("Calculation", "onTextChanged: " + scavms + " scavms " + stl + " stl " + spd + " spd " + spd + "  " + ssdp + " !");

                                //String sidtm = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);
                                //String sidtms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);

                                //if (Double.valueOf(scavms) != 0 && Double.valueOf(stl) != 0 && Double.valueOf(spd) != 0 && Double.valueOf(ssdp) != 0) {
                                Double up = 1.6 * pow(10, 3) * pow(Double.valueOf(scavms), 1.85) * Double.valueOf(stl);
                                Double down = /*pow(10, 10)*/ 10_000_000_000.0 * Double.valueOf(spd) * Double.valueOf(ssdp);

                                Log.d("TestT", "???  " + scavms + " up1 " + stl + " down1 " + pow(up / down, 0.2) + " pow ");

/*                                Double test = pow(up / down, 0.2);
                                Double test1 = up / down;
                                Double test2 = pow(test1, 0.2);*/
                                //Log.d("lenght", "onTextChanged: " + test + " h " + up/down + " j " + String.format("%.6f", test));
                                idtm.setText(String.format("%.6f", pow(up / down, 0.2)));
                                idtms.setText(String.format("%.6f", pow(up / down, 0.2) * 1_000));   // Decimal problem Scientific notation NOT 1/5, 0.2 - good

                                Log.d("Test", "???  " + up + " up " + down + " down " + pow(up / down, 0.2) + " pow ");

                                //idtm.setText(String.format("%.6f", test2));
                                //idtms.setText(String.format("%.6f", test2 * 1_000));

/*                                Log.d("lenght", "onTextChanged: " + test1 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + up + " up " + down + " down ");
                                Log.d("lenght", "onTextChanged: " + test2 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + pow(up / down, (1 / 5)) + " up " + pow(up / down, (1 / 5)) * 1_000 + " down ");*/
                                //}


                                //cavmm.setText(scavmm);

                        /*
                        tl.setText(stl);
                        pd.setText(spd);
                        sdp.setText(ssdp);
                        idtm.setText(sidtm);
                        idtms.setText(sidtms);*/


                            }

                        } catch (NumberFormatException nfe) {
                            cavmm.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                } else {
                    cavms.setText("");
                    idtm.setText("");
                    idtms.setText("");
                    return;
                }

            }
        });

        cavmm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////





        tl.addTextChangedListener(new TextWatcher() {

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

                        //String scavmm = String.valueOf(Float.valueOf(cavmm.getText().toString()) * 59.988002);
                        //Log.d("lenght", "onTextChanged:" + tl.getText().toString() + "a" + pd.getText().toString() + "f" + sdp.getText().toString() + "!!");

                        //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                        //cavms.setText(scavms);

                        if (tl.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            tl.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            if (cavms.getText().toString().trim().length() > 0 && tl.getText().toString().trim().length() > 0 && pd.getText().toString().trim().length() > 0 && sdp.getText().toString().trim().length() > 0 ) {

                                //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);

                                String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                                String stl = String.format("%.6f", Float.valueOf(tl.getText().toString()));
                                String spd = String.format("%.6f", Float.valueOf(pd.getText().toString()));
                                String ssdp = String.format("%.6f", Float.valueOf(sdp.getText().toString()));

                                scavms = scavms.replace(",", ".");
                                stl = stl.replace(",", ".");
                                spd = spd.replace(",", ".");
                                ssdp = ssdp.replace(",", ".");

                                Log.d("Calculation", "onTextChanged: " + scavms + " scavms " + stl + " stl " + spd + " spd " + spd + "  " + ssdp + " !");

                                //String sidtm = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);
                                //String sidtms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);

                                //if (Double.valueOf(scavms) != 0 && Double.valueOf(stl) != 0 && Double.valueOf(spd) != 0 && Double.valueOf(ssdp) != 0) {
                                Double up = 1.6 * pow(10, 3) * pow(Double.valueOf(scavms), 1.85) * Double.valueOf(stl);
                                Double down = /*pow(10, 10)*/ 10_000_000_000.0 * Double.valueOf(spd) * Double.valueOf(ssdp);

                                Log.d("TestT", "???  " + scavms + " up1 " + stl + " down1 " + pow(up / down, 0.2) + " pow ");

/*                                Double test = pow(up / down, 0.2);
                                Double test1 = up / down;
                                Double test2 = pow(test1, 0.2);*/
                                //Log.d("lenght", "onTextChanged: " + test + " h " + up/down + " j " + String.format("%.6f", test));
                                idtm.setText(String.format("%.6f", pow(up / down, 0.2)));
                                idtms.setText(String.format("%.6f", pow(up / down, 0.2) * 1_000));   // Decimal problem Scientific notation NOT 1/5, 0.2 - good

                                Log.d("Test", "???  " + up + " up " + down + " down " + pow(up / down, 0.2) + " pow ");

                                //idtm.setText(String.format("%.6f", test2));
                                //idtms.setText(String.format("%.6f", test2 * 1_000));

/*                                Log.d("lenght", "onTextChanged: " + test1 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + up + " up " + down + " down ");
                                Log.d("lenght", "onTextChanged: " + test2 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + pow(up / down, (1 / 5)) + " up " + pow(up / down, (1 / 5)) * 1_000 + " down ");*/
                                //}


                                //cavmm.setText(scavmm);

                        /*
                        tl.setText(stl);
                        pd.setText(spd);
                        sdp.setText(ssdp);
                        idtm.setText(sidtm);
                        idtms.setText(sidtms);*/


                            }

                        } catch (NumberFormatException nfe) {
                            tl.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                } else {
                    idtm.setText("");
                    idtms.setText("");
                    return;
                }

            }
        });

        tl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////





        pd.addTextChangedListener(new TextWatcher() {

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

                        if (pd.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            pd.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            //String scavmm = String.valueOf(Float.valueOf(cavmm.getText().toString()) * 59.988002);
                            //Log.d("lenght", "onTextChanged:" + tl.getText().toString() + "a" + pd.getText().toString() + "f" + sdp.getText().toString() + "!!");

                            //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                            //cavms.setText(scavms);
                            //String df = (sdp.getText().equals("") ? "true" : "false");
                            Log.d("PD", "onTextChanged:" + (sdp.getText().toString().trim().length() > 0 ? "true":"false") + "!");
                            Log.d("print", "onTextChanged:" + sdp + "!" + sdp.getText().toString().trim().length() + "lenght");
                            if (cavms.getText().toString().trim().length() > 0 && tl.getText().toString().trim().length() > 0 && pd.getText().toString().trim().length() > 0 && sdp.getText().toString().trim().length() > 0 )  {

                                //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);

                                String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                                String stl = String.format("%.6f", Float.valueOf(tl.getText().toString()));
                                String spd = String.format("%.6f", Float.valueOf(pd.getText().toString()));
                                String ssdp = String.format("%.6f", Float.valueOf(sdp.getText().toString()));

                                scavms = scavms.replace(",", ".");
                                stl = stl.replace(",", ".");
                                spd = spd.replace(",", ".");
                                ssdp = ssdp.replace(",", ".");

                                Log.d("Calculation", "onTextChanged: " + scavms + " scavms " + stl + " stl " + spd + " spd " + spd + "  " + ssdp + " !");

                                //String sidtm = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);
                                //String sidtms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);

                                //if (Double.valueOf(scavms) != 0 && Double.valueOf(stl) != 0 && Double.valueOf(spd) != 0 && Double.valueOf(ssdp) != 0) {
                                Double up = 1.6 * pow(10, 3) * pow(Double.valueOf(scavms), 1.85) * Double.valueOf(stl);
                                Double down = /*pow(10, 10)*/ 10_000_000_000.0 * Double.valueOf(spd) * Double.valueOf(ssdp);

                                Log.d("TestT", "???  " + scavms + " up1 " + stl + " down1 " + pow(up / down, 0.2) + " pow ");

/*                                Double test = pow(up / down, 0.2);
                                Double test1 = up / down;
                                Double test2 = pow(test1, 0.2);*/
                                //Log.d("lenght", "onTextChanged: " + test + " h " + up/down + " j " + String.format("%.6f", test));
                                idtm.setText(String.format("%.6f", pow(up / down, 0.2)));
                                idtms.setText(String.format("%.6f", pow(up / down, 0.2) * 1_000));   // Decimal problem Scientific notation NOT 1/5, 0.2 - good

                                Log.d("Test", "???  " + up + " up " + down + " down " + pow(up / down, 0.2) + " pow ");

                                //idtm.setText(String.format("%.6f", test2));
                                //idtms.setText(String.format("%.6f", test2 * 1_000));

/*                                Log.d("lenght", "onTextChanged: " + test1 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + up + " up " + down + " down ");
                                Log.d("lenght", "onTextChanged: " + test2 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + pow(up / down, (1 / 5)) + " up " + pow(up / down, (1 / 5)) * 1_000 + " down ");*/
                                //}


                                //cavmm.setText(scavmm);

                        /*
                        tl.setText(stl);
                        pd.setText(spd);
                        sdp.setText(ssdp);
                        idtm.setText(sidtm);
                        idtms.setText(sidtms);*/


                            }

                        } catch (NumberFormatException nfe) {
                            pd.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                } else {
                    idtm.setText("");
                    idtms.setText("");
                    return;
                }

            }
        });

        pd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });



        ////////////////////////////////////////////////////////////////////////////////////////





        sdp.addTextChangedListener(new TextWatcher() {

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

                        if (sdp.getText().toString().replaceFirst("[.]", "").trim().equals("")) {
                            sdp.setText("");
                            ignoreChange = false;
                            return;
                        }

                        try {

                            //String scavmm = String.valueOf(Float.valueOf(cavmm.getText().toString()) * 59.988002);
                            //Log.d("lenght", "onTextChanged:" + tl.getText().toString() + "a" + pd.getText().toString() + "f" + sdp.getText().toString() + "!!");

                            //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                            //cavms.setText(scavms);
                            Log.d("PD", "onTextChanged:" + sdp.getText() + "!");
                            if (cavms.getText().toString().trim().length() > 0 && tl.getText().toString().trim().length() > 0 && pd.getText().toString().trim().length() > 0 && sdp.getText().toString().trim().length() > 0 ) {

                                //String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);

                                String scavms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 0.016666);
                                String stl = String.format("%.6f", Float.valueOf(tl.getText().toString()));
                                String spd = String.format("%.6f", Float.valueOf(pd.getText().toString()));
                                String ssdp = String.format("%.6f", Float.valueOf(sdp.getText().toString()));

                                scavms = scavms.replace(",", ".");
                                stl = stl.replace(",", ".");
                                spd = spd.replace(",", ".");
                                ssdp = ssdp.replace(",", ".");

                                Log.d("Calculation", "onTextChanged: " + scavms + " scavms " + stl + " stl " + spd + " spd " + spd + "  " + ssdp + " !");

                                //String sidtm = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);
                                //String sidtms = String.format("%.6f", Float.valueOf(cavmm.getText().toString()) * 1.013);

                                //if (Double.valueOf(scavms) != 0 && Double.valueOf(stl) != 0 && Double.valueOf(spd) != 0 && Double.valueOf(ssdp) != 0) {
                                Double up = 1.6 * pow(10, 3) * pow(Double.valueOf(scavms), 1.85) * Double.valueOf(stl);
                                Double down = /*pow(10, 10)*/ 10_000_000_000.0 * Double.valueOf(spd) * Double.valueOf(ssdp);

                                Log.d("TestT", "???  " + scavms + " up1 " + stl + " down1 " + pow(up / down, 0.2) + " pow ");

/*                                Double test = pow(up / down, 0.2);
                                Double test1 = up / down;
                                Double test2 = pow(test1, 0.2);*/
                                //Log.d("lenght", "onTextChanged: " + test + " h " + up/down + " j " + String.format("%.6f", test));
                                idtm.setText(String.format("%.6f", pow(up / down, 0.2)));
                                idtms.setText(String.format("%.6f", pow(up / down, 0.2) * 1_000));   // Decimal problem Scientific notation NOT 1/5, 0.2 - good

                                Log.d("Test", "???  " + up + " up " + down + " down " + pow(up / down, 0.2) + " pow ");

                                //idtm.setText(String.format("%.6f", test2));
                                //idtms.setText(String.format("%.6f", test2 * 1_000));

/*                                Log.d("lenght", "onTextChanged: " + test1 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + up + " up " + down + " down ");
                                Log.d("lenght", "onTextChanged: " + test2 + " test " + scavms + " " + stl + " " + spd + " " + ssdp + " !" + pow(up / down, (1 / 5)) + " up " + pow(up / down, (1 / 5)) * 1_000 + " down ");*/
                                //}


                                //cavmm.setText(scavmm);

                        /*
                        tl.setText(stl);
                        pd.setText(spd);
                        sdp.setText(ssdp);
                        idtm.setText(sidtm);
                        idtms.setText(sidtms);*/


                            }

                        } catch (NumberFormatException nfe) {
                            sdp.setText("");
                            ignoreChange = false;
                            return;
                        }

                        ignoreChange = false;
                    }

                } else {
                    idtm.setText("");
                    idtms.setText("");
                    return;
                }

            }
        });

        sdp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////




 /*       kpa.addTextChangedListener(new TextWatcher() {

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
        });*/


    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
