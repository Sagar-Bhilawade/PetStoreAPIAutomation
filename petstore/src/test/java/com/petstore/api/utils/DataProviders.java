package com.petstore.api.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;


public class DataProviders {

    private static final String TEST_DATA_FILE = "UserData.xlsx";

    private String resolveTestDataPath() {
        Path baseDir = Paths.get(System.getProperty("user.dir"));
        Path[] candidates = new Path[] {
            baseDir.resolve("testData").resolve(TEST_DATA_FILE),
            baseDir.resolve("testdata").resolve(TEST_DATA_FILE),
            baseDir.resolve("petstore").resolve("testData").resolve(TEST_DATA_FILE),
            baseDir.resolve("petstore").resolve("testdata").resolve(TEST_DATA_FILE)
        };

        for (Path candidate : candidates) {
            if (Files.exists(candidate)) {
                return candidate.toString();
            }
        }

        return candidates[0].toString();
    }
    
    @DataProvider(name = "getUserData")
    public String[][] getUserData() {
        String path = resolveTestDataPath();
        XLUtility xlUtility = new XLUtility(path);
        int rowCount = xlUtility.getRowCount("Sheet1");
        int colCount = xlUtility.getCellCount("Sheet1", 1);
        String userData[][] = new String[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                userData[i-1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        }
        return userData;
    }

    @DataProvider(name = "UserNames")
    public String[][] getUserNames() {
        String path = resolveTestDataPath();
        XLUtility xlUtility = new XLUtility(path);
        int rowCount = xlUtility.getRowCount("Sheet1");
        String apiData[][] = new String[rowCount][1];
        for (int i = 1; i <= rowCount; i++) {
            apiData[i-1][0] = xlUtility.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}
