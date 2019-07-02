# Hot Restart Example Walk Through

This example demonstrates the basic usage of the Hazelcast Hot Restart Store
feature.  For a detailed description of how to use and configure Hot Restart,
see [here](https://docs.hazelcast.org/docs/3.12/manual/html-single/index.html#hot-restart-persistence).

1. Edit `hazelcast.xml` and include your license in the indicated location.
2. Set the JAVA_HOME environment variable to point to the JDK you want to use.
 $JAVA_HOME/bin/java should be a valid java 1.8 java runtime.
   ```
   # yours may be different
   export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
   ```
3. Build the project: `mvn package`
4. Start a 2 node cluster (convenience script included).
   ```
   Randys-MacBook-Pro:hazelcast-hot-restart-example randy$ ./startcluster.sh
   A local cluster launched. Log files are in server1.log and server2.log
   ```
5. Follow the progress of startup by tailing the logs.
   ```
   tail -f server*.log
   ```
6. Start Hazelcast Management Center and verify that you can see both members.
   You will need to use Management Center to stop the cluster so don't skip
   this step!

7. Run the loader program using the included script.  It will print the current
   contents of the "person" map then add 5 more entries. Example below
   ```
   Randys-MacBook-Pro:hazelcast-hot-restart-example randy$ ./startloader.sh
   Jul 02, 2019 11:52:54 AM com.hazelcast.config.AbstractConfigLocator
   INFO: Loading 'hazelcast-client.xml' from the working directory.
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.HazelcastClient
   INFO: hz.client_0 [dev] [3.12.1] A non-empty group password is configured for the Hazelcast client. Starting with Hazelcast version 3.11, clients with the same group name, but with different group passwords (that do not use authentication) will be accepted to a cluster. The group password configuration will be removed completely in a future release.
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.spi.ClientInvocationService
   INFO: hz.client_0 [dev] [3.12.1] Running with 2 response threads, dynamic=false
   Jul 02, 2019 11:52:55 AM com.hazelcast.core.LifecycleService
   INFO: hz.client_0 [dev] [3.12.1] HazelcastClient 3.12.1 (20190611 - 750b050, 0a0ee66) is STARTING
   Jul 02, 2019 11:52:55 AM com.hazelcast.core.LifecycleService
   INFO: hz.client_0 [dev] [3.12.1] HazelcastClient 3.12.1 (20190611 - 750b050, 0a0ee66) is STARTED
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.connection.nio.ClusterConnectorService
   INFO: hz.client_0 [dev] [3.12.1] Trying to connect to cluster with name: dev
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.connection.nio.ClusterConnectorService
   INFO: hz.client_0 [dev] [3.12.1] Trying to connect to [localhost]:5701 as owner member
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.connection.ClientConnectionManager
   INFO: hz.client_0 [dev] [3.12.1] Setting ClientConnection{alive=true, connectionId=1, channel=NioChannel{/127.0.0.1:51456->localhost/127.0.0.1:5701}, remoteEndpoint=[localhost]:5701, lastReadTime=2019-07-02 11:52:55.463, lastWriteTime=2019-07-02 11:52:55.448, closedTime=never, connected server version=3.12.1} as owner with principal ClientPrincipal{uuid='ef262f0d-23eb-44bd-9d84-cce8a79889fa', ownerUuid='46e4e645-7b8e-4126-9b4d-72cc4e778f81'}
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.connection.ClientConnectionManager
   INFO: hz.client_0 [dev] [3.12.1] Authenticated with server [localhost]:5701, server version:3.12.1 Local address: /127.0.0.1:51456
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.spi.impl.ClientMembershipListener
   INFO: hz.client_0 [dev] [3.12.1]

   Members [2] {
   	Member [localhost]:5701 - 46e4e645-7b8e-4126-9b4d-72cc4e778f81
   	Member [localhost]:5702 - 12bfc05f-fa58-4f4f-85a7-14de47c9864f
   }

   Jul 02, 2019 11:52:55 AM com.hazelcast.core.LifecycleService
   INFO: hz.client_0 [dev] [3.12.1] HazelcastClient 3.12.1 (20190611 - 750b050, 0a0ee66) is CLIENT_CONNECTED
   Jul 02, 2019 11:52:55 AM com.hazelcast.internal.diagnostics.Diagnostics
   INFO: hz.client_0 [dev] [3.12.1] Diagnostics disabled. To enable add -Dhazelcast.diagnostics.enabled=true to the JVM arguments.
   Jul 02, 2019 11:52:55 AM com.hazelcast.demo.Loader
   INFO: got person map
   Jul 02, 2019 11:52:55 AM com.hazelcast.client.connection.ClientConnectionManager
   INFO: hz.client_0 [dev] [3.12.1] Authenticated with server [localhost]:5702, server version:3.12.1 Local address: /127.0.0.1:51457
   person map is empty
   ADDED 0000: Person{lastName='Streich', firstName='Olinda', street1='74246 Willms Plains', city='Bradtkefurt', state='RI', zip='029##'}
   ADDED 0001: Person{lastName='Koepp', firstName='Huey', street1='8651 Zora Stravenue', city='Wilmastad', state='SC', zip='299##'}
   ADDED 0002: Person{lastName='Lockman', firstName='Ellsworth', street1='732 Kessler Views', city='New Willisbury', state='RI', zip='029##'}
   ADDED 0003: Person{lastName='Schamberger', firstName='Norman', street1='2474 Jast Road', city='North Serina', state='VA', zip='222##'}
   ADDED 0004: Person{lastName='Gutmann', firstName='Kasey', street1='6628 Haydee Fork', city='West Piedad', state='WA', zip='990##'}
   Jul 02, 2019 11:52:55 AM com.hazelcast.core.LifecycleService
   INFO: hz.client_0 [dev] [3.12.1] HazelcastClient 3.12.1 (20190611 - 750b050, 0a0ee66) is SHUTTING_DOWN
   Jul 02, 2019 11:52:56 AM com.hazelcast.client.connection.ClientConnectionManager
   INFO: hz.client_0 [dev] [3.12.1] Removed connection to endpoint: [localhost]:5701, connection: ClientConnection{alive=false, connectionId=1, channel=NioChannel{/127.0.0.1:51456->localhost/127.0.0.1:5701}, remoteEndpoint=[localhost]:5701, lastReadTime=2019-07-02 11:52:55.914, lastWriteTime=2019-07-02 11:52:55.913, closedTime=2019-07-02 11:52:55.915, connected server version=3.12.1}
   Jul 02, 2019 11:52:57 AM com.hazelcast.client.connection.ClientConnectionManager
   INFO: hz.client_0 [dev] [3.12.1] Removed connection to endpoint: [localhost]:5702, connection: ClientConnection{alive=false, connectionId=2, channel=NioChannel{/127.0.0.1:51457->localhost/127.0.0.1:5702}, remoteEndpoint=[localhost]:5702, lastReadTime=2019-07-02 11:52:55.908, lastWriteTime=2019-07-02 11:52:55.907, closedTime=2019-07-02 11:52:56.919, connected server version=3.12.1}
   Jul 02, 2019 11:52:57 AM com.hazelcast.core.LifecycleService
   INFO: hz.client_0 [dev] [3.12.1] HazelcastClient 3.12.1 (20190611 - 750b050, 0a0ee66) is SHUTDOWN   
   ```

8. Use Managment Center to shut down the cluster (Administration > Shutdown)

   __Note: when using Hot Restart Store it is critical to shut the cluster down
   in an orderly fashion__. Failing to do so is likely to cause corruption
   of the underlying files and yield them unusable.

9. Use the logs to verify that the cluster has stopped.  An example from a clean
   shutdown log is below.

   ```
   Jul 02, 2019 12:00:44 PM com.hazelcast.instance.Node
INFO: [localhost]:5702 [dev] [3.12.1] Hazelcast Shutdown is completed in 1020 ms.
Jul 02, 2019 12:00:44 PM com.hazelcast.core.LifecycleService
INFO: [localhost]:5702 [dev] [3.12.1] [localhost]:5702 is SHUTDOWN
   ```

10. Start the cluster again using the same script. Verify by tailing the logs
    that the cluster does come up cleanly.

11. Using Management Center, verify that the "person" map already contains
    5 entries (because they were loaded from the Hot Restart Store)

12. Run the loader one more time and verify that it prints out the person
    entries that were loaded last time.

13. Shut down the cluster again using Management Center.
