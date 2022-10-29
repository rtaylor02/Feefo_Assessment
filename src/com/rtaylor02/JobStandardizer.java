package com.rtaylor02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobStandardizer {
    private List<String> standardTitles = new ArrayList<>();

    public JobStandardizer() {
        standardTitles.add("Software engineer");
        standardTitles.add("Accountant");
        standardTitles.add("Architect");
        standardTitles.add("Quantity surveyor");
    }

    public String standardize(String inputTitle) {
        if(inputTitle != null) {
            if (calculateQualityScore(inputTitle) > 0.0) {
                return standardTitles.get(matchStandardTitle(inputTitle));
            } else {
                return "";
            }
        } else {
            throw new NullPointerException("input title cannot be null");
        }
    }

    // Return index of matching standard title if any. -1 if no match.
    int matchStandardTitle(String title) {
        int result = -1;

        String[] titleArr = title.split(" ");
        for(int i = 0; ((i < standardTitles.size()) && (result == -1)); i++) {
            String[] ref = standardTitles.get(i).split(" ");

            // Compare main titles, e.g. Java engineer (main title = engineer)
            if((ref[ref.length-1].toLowerCase()).equals((titleArr[titleArr.length-1]).toLowerCase())) {
                result = i;
            }
        }

        return result;
    }

    // Calculate quality of match when any matching standard title is found.
    double calculateQualityScore(String title) {
        double q = 0.0;

        int matchIndex = matchStandardTitle(title);
        if(matchIndex >= 0) {
            String[] ref = standardTitles.get(matchIndex).split(" ");
            Arrays.sort(ref,String.CASE_INSENSITIVE_ORDER);

            String[] titleArr = title.split(" ");
            for(String s : titleArr) {
                if(Arrays.binarySearch(ref, s, String.CASE_INSENSITIVE_ORDER) >= 0) {
                    q++;
                }
            }

            q = q/titleArr.length;
        }

        return q;
    }
}
