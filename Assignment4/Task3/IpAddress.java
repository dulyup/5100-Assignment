package com.assign4;

import java.util.ArrayList; // score 2

public class IpAddress {
    private String dottedDecimal;
    private ArrayList<Integer> octets = new ArrayList();

    public IpAddress(String dottedDecimal) {
        this.dottedDecimal = dottedDecimal;
        generateOctetsArray(dottedDecimal);
    }

    public void generateOctetsArray(String dottedDecimal){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dottedDecimal.length(); i++) {

            if (dottedDecimal.charAt(i) == '.') {
                octets.add(Integer.parseInt(sb.toString()));
                sb.delete(0, sb.length());
                continue;
            }

            sb.append(dottedDecimal.charAt(i));

        }
        octets.add(Integer.parseInt(sb.toString()));
    }

    public int getOctet(int position) {
        return octets.get(position - 1);
    }

    public String getDottedDecimal() {
        return dottedDecimal;
    }
}
