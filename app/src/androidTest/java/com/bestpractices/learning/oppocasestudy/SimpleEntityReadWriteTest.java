package com.bestpractices.learning.oppocasestudy;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.bestpractices.learning.oppocasestudy.models.Funds;
import com.bestpractices.learning.oppocasestudy.repository.FundsDao;
import com.bestpractices.learning.oppocasestudy.repository.FundsDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private FundsDao userDao;
    private FundsDatabase db;
    public static final String TAG = "DB_Test";

    @Before
    public void createDb() {
        Context context;
        context = InstrumentationRegistry.getInstrumentation().getContext();
        db = Room.inMemoryDatabaseBuilder(context, FundsDatabase.class).build();
        userDao = db.fundsDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {

        Funds fundsModel = new Funds( "Fcfa",  "icici",  "3 year",  "18.6",  "equity",  "3",  "url");

        userDao.insert(fundsModel);

        List<Funds> fundsList = userDao.getAllFundsFromDb();
        Funds sampleModel = fundsList.get(0);
        assertThat(sampleModel.getFundCode(), equalTo(fundsModel.getFundCode()));
        assertThat(sampleModel.getFundName(), equalTo(fundsModel.getFundName()));
        assertThat(sampleModel.getFundCategory(), equalTo(fundsModel.getFundCategory()));
        assertThat(sampleModel.getThreeYearReturns(), equalTo(fundsModel.getThreeYearReturns()));
        assertThat(sampleModel.getNav(), equalTo(fundsModel.getNav()));
        assertThat(sampleModel.getRating(), equalTo(fundsModel.getRating()));
        assertThat(sampleModel.getUrl(), equalTo(fundsModel.getUrl()));
    }
}
