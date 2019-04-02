package com.example.task1;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;

@Database(entities = {Book.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;


    public abstract DAO dao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private DAO dao;
        private PopulateDbAsyncTask(AppDatabase db){
            dao=db.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insertAll(new Book("English","I want to successful","orem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in placerat urna. Phasellus varius Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus hendrerit bibendum placerat. Donec et diam facilisis, posuere mauris sed, congue tortor. Morbi in rutrum nibh. Curabitur condimentum nunc sapien, id pharetra tellus ullamcorper sit amet. Nam suscipit dolor felis, eu mollis velit dictum sed. Nunc non felis non magna imperdiet pulvinar. Pellentesque in placerat elit. Sed vitae diam fringilla, maximus justo id, cursus ante. Integer non tincidunt velit. Vestibulum venenatis convallis interdum. Donec mollis eu augue quis tempor","http://images.amazon.com/images/P/0596004478.01._PE30_PI_SCMZZZZZZZ_.jpg"));
            dao.insertAll(new Book("Chinese","I am Chinese","Sed erat dui, ultrices quis molestie eleifend, commodo vel urna. Morbi porta tellus at consequat pellentesque. Cras quis posuere velit. Praesent quis Mauris lacus elit, fermentum a volutpat in, sodales varius justo. Donec ac ante iaculis lorem tempus fermentum. Morbi imperdiet nibh at orci molestie vehicula. Integer tortor nisi, facilisis nec elementum sed, viverra in neque. Morbi hendrerit porttitor turpis, quis tincidunt ipsum pellentesque non. Quisque ornare lacinia mattis. Aenean at ante eget lacus auctor imperdiet. Orci varius natoque penatibus et magnis dis ","http://images.amazon.com/images/P/0596004613.01._PE30_PI_SCMZZZZZZZ_.jpg"));
            dao.insertAll(new Book("Malay","I am Malay"," natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras porttitor dolor id augue venenatis dignissim. Nam feugiat Mauris id lectus at sem lobortis rutrum sed eu lectus. Vestibulum augue leo, aliquam vel magna eget, sollicitudin imperdiet justo. Donec aliquam facilisis velit in dapibus. Mauris ipsum metus, maximus vitae laoreet id, cursus et enim. Phasellus nulla mi, consectetur at purus sit amet, varius gravida sem. Morbi cursus neque ac metus venenatis sollicitudin. Sed non finibus tellus.","https://homepages.cae.wisc.edu/~ece533/images/cat.png"));
            dao.insertAll(new Book("Indian","I am Indian","convallis efficitur ligula a, aliquam sollicitudin orci. Quisque non sodales magna. Sed fringilla, leo vel pretium vehicula, tortor Suspendisse ut dolor tellus. Nunc ut purus tortor. Ut at semper purus. Mauris quis nisi faucibus, tempus nisi et, tincidunt elit. Aenean laoreet metus ante, ac imperdiet sem sollicitudin vulputate. Pellentesque vestibulum, sem vitae blandit facilisis, elit leo ultrices massa, quis consequat lacus nunc et ipsum. Praesent lobortis non ante pharetra viverra. Sed pharetra finibus magna, id consequat dui egestas at. Sed feugiat, nisl non volutpat dapibus, purus nisi fermentum lectus, non rutrum nibh felis non diam","http://images.amazon.com/images/P/0596004605.01._PE50_PI_SCMZZZZZZZ_.jpg"));
            dao.insertAll(new Book("Korea","I am Korean","Pellentesque posuere euismod nisi id aliquet. Vestibulum aliquet viverra nibh vitae porta. Aliquam dapibus sit amet metus vitae rhoncus uspendisse vehicula mi posuere facilisis gravida. In tristique posuere fringilla. Ut lacinia non nisl at sodales. Nullam tempus sed nisl nec consectetur. Duis non tempor dolor. Praesent pellentesque eros quis lectus iaculis vehicula. Duis diam felis, facilisis ut sem quis, porta facilisis ligula. Suspendisse vitae cursus massa. Vivamus felis eros, cursus ut rutrum at, efficitur eget purus.","https://archive.org/download/panamapacificint00moor/page/page15_x280_y292_w668_h1080_s4.jpg"));

            dao.insertAll(new Book("Cooking","I want to cooking","orem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in placerat urna. Phasellus varius Nulla vitae mi vel massa maximus varius sed id neque. Nulla elementum auctor odio. In malesuada porta ultrices. Sed vulputate orci non faucibus aliquet. Etiam non elit et enim gravida maximus eget a enim. Maecenas turpis velit, viverra non sem consectetur, ultricies rhoncus odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur fringilla libero non lacinia dignissim. Sed ut sapien pharetra, semper lacus sit amet,","http://images.amazon.com/images/P/0596004478.01._PE30_PI_SCMZZZZZZZ_.jpg"));
            dao.insertAll(new Book("Farming","I love farming","Sed erat dui, ultrices quis molestie eleifend, commodo vel urna. Morbi porta tellus at consequat pellentesque. Cras quis posuere velit. Praesent quis uam eu accumsan quam. Maecenas semper massa ut est ornare, id efficitur magna luctus. Nulla ac rutrum sapien. Nam vitae elementum ipsum. Quisque ligula ligula, facilisis in suscipit vitae, pretium id risus. Donec nec dignissim lacus, at elementum nisi. ","https://homepages.cae.wisc.edu/~ece533/images/arctichare.png"));
            dao.insertAll(new Book("Gaming","I am gamer"," natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras porttitor dolor id augue venenatis dignissim. Nam feugiat Aliquam consectetur ultricies ipsum eu suscipit. Curabitur sodales mollis lobortis. Aliquam erat volutpat. Curabitur non efficitur libero, et mattis enim. Sed id sollicitudin nisl, sodales dignissim nisl. Sed venenatis lorem ac ex consectetur, ut suscipit mauris maximus. Nam lobortis placerat tortor, hendrerit mollis mi commodo nec. Morbi sagittis elementum mi at pulvinar. Curabitur fringilla sagittis ornare. Integer mollis libero a quam sagittis consectetur. Integer at libero non urna mollis auctor","https://homepages.cae.wisc.edu/~ece533/images/airplane.png"));
            dao.insertAll(new Book("Coding","I love coding","convallis efficitur ligula a, aliquam sollicitudin orci. Quisque non sodales magna. Sed fringilla, leo vel pretium vehicula, tortor Etiam consectetur nisi et sem dictum finibus. Aenean eget turpis et tortor dictum aliquam. Maecenas varius tristique elit, quis dapibus justo suscipit vel. Nunc lacinia nisl eget ligula pharetra aliquet. Suspendisse placerat malesuada interdum. Nunc eleifend risus felis, eu rhoncus orci suscipit eget. Praesent a erat massa. Vestibulum vel accumsan felis. Morbi iaculis purus in felis ullamcorper, vitae maximus neque interdum. Nulla sit amet sem aliquam, porttitor purus sed, feugiat augue. Sed ut maximus sem","https://homepages.cae.wisc.edu/~ece533/images/baboon.png"));
            dao.insertAll(new Book("Dessert","I love dessert","Pellentesque posuere euismod nisi id aliquet. Vestibulum aliquet viverra nibh vitae porta. Aliquam dapibus sit amet metus vitae rhoncus Integer tristique, magna eget scelerisque posuere, mauris diam condimentum urna, id ultricies purus mi sed urna. Sed sed vulputate quam. Vestibulum nec libero at nisl vehicula dapibus. Proin lacinia placerat eros, sit amet interdum enim congue sed. Donec vehicula diam urna, eget auctor lectus facilisis vitae. Fusce feugiat, enim in facilisis pretium, nibh nibh tempor justo, in maximus libero urna ut ligula","https://homepages.cae.wisc.edu/~ece533/images/boat.png"));
            return null;

        }
    }


   /* private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "my-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).dao().insertAll(Book.populateData());
                            }
                        });
                    }
                })
                .build();
    }*/


}
