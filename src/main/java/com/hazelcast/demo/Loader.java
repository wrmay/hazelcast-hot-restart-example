package com.hazelcast.demo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Loader {
    public static void main(String []args){
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
        ILogger log = Logger.getLogger(Loader.class);
        IMap<Integer, Person> personMap = hz.getMap("person");
        log.info("got person map");

        Set<Integer> entries = personMap.keySet();
        ArrayList<Integer> sortedKeys = new ArrayList<>(entries.size());
        sortedKeys.addAll(entries);
        Collections.sort(sortedKeys);
        int count = entries.size();
        if (count ==0){
            System.out.println("person map is empty");
        } else {
            System.out.println("BEGIN PERSON MAP CONTENTS");
            for (Integer key : sortedKeys) {
                System.out.println(String.format("%04d: %s", key, personMap.get(key).toString()));
            }
            System.out.println("END PERSON MAP CONTENTS");
        }

        for(int i = 0; i < 5; ++i){
            Person p = Person.fakePerson();
            personMap.set(count + i, p);
            System.out.println(String.format("ADDED %04d: %s", count + i, p));
        }


        hz.shutdown();
    }
}
