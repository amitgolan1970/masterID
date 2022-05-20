package com.golan.amit.masterid;

public class IdHelper {

    public static final int NUMBER_OF_DIGITS = 8;
    private String _id[];
    private int control;

    private int fails;

    /**
     * Constructor
     */
    public IdHelper() {
        _id = new String[NUMBER_OF_DIGITS];
        this.fails = 1;
    }

    public void generate() {
        for(int i = 0; i < NUMBER_OF_DIGITS; i++) {
            get_id()[i] = "" + (int)(Math.random() * 10);
        }
    }

    public void set_id_by_string(String idStr) {
        for(int i = 0; i < idStr.length(); i++) {
            if(i < NUMBER_OF_DIGITS)
                get_id()[i] = String.valueOf(idStr.charAt(i));
        }
    }

    public void calculateControl() {
        System.out.println("calculating for: " + stringRepresentation());
        int cnt = 1;
        int tmpSum = 0;

        for(int i = 0; i < get_id().length; i++) {
            if(cnt % 2 == 0) {
                int tmpDigit = 2 * (Integer.parseInt(_id[i]));
                if(tmpDigit > 9) {
                    tmpSum += 1 + (tmpDigit % 10);
                } else {
                    tmpSum += tmpDigit;
                }
            } else {
                tmpSum += Integer.parseInt(_id[i]);
            }
            cnt++;
        }
        System.out.println(tmpSum);
        setControl(10 - (tmpSum % 10));
        if(getControl() == 10)
            setControl(0);
        System.out.println(getControl());
    }

    public String[] get_id() {
        return _id;
    }

    public void set_id(String[] _id) {
        this._id = _id;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < get_id().length; i++) {
            sb.append(get_id()[i]);
        }
        return sb.toString();
    }

    public String HelpHint() {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < get_id().length; i++) {
            if(cnt % 2 == 0) {
                sb.append(get_id()[i]);
            } else {
                int tmpDig = Integer.parseInt(get_id()[i]);
                tmpDig *= 2;
                if(tmpDig < 10) {
                    sb.append(String.valueOf(tmpDig));
                } else {
                    sb.append("(1 + " + tmpDig % 10 + ")");
                }
            }
            if(i < get_id().length - 1) {
                sb.append(" + ");
            }
            cnt++;
        }
        sb.append(" = ?");
        return sb.toString();
    }

    public String stringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < get_id().length; i++) {
            sb.append(get_id()[i]);
        }
        return sb.toString();
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public void increaseFails() {
        this.fails++;
    }
}
