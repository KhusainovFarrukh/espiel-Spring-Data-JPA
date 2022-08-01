Transactions (@Transactional)

https://www.youtube.com/watch?v=SUQxXg229Xg
https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
https://www.baeldung.com/spring-transactions-read-only

Spring’s transaction management is based on the transaction management provided by your database and the JDBC specification

Transactions manage the changes that you perform in one or more systems. These can be databases, message brokers, 
or any other kind of software system. The main goal of a transaction is to provide ACID characteristics 
to ensure the consistency and validity of your data

ACID - Atomicity, Consistency, Isolation, Durability

ATOMICITY describes an all or nothing principle. Either all operations performed within the transaction 
get executed or none of them. That means if you commit the transaction successfully, you can be sure that 
all operations got performed. It also enables you to abort a transaction and roll back all operations 
if an error occurs.
CONSISTENCY characteristic ensures that your transaction takes a system from one consistent state 
to another consistent state. That means that either all operations were rolled back and the data was set back 
to the state you started with or the changed data passed all consistency checks. In a relational database, 
that means that the modified data needs to pass all constraint checks, like foreign key or unique constraints, 
defined in your database.
ISOLATION means that changes that you perform within a transaction are not visible to any other transactions until 
you commit them successfully.
DURABILITY ensures that your committed changes get persisted.

----------------------------------------------

Spring provides all the boilerplate code that’s required to start, commit, or rollback a transaction. 
It also integrates with Hibernate’s and JPA’s transaction handling. If you’re using Spring Boot, 
this reduces your effort to a @Transactional annotation on each interface, method, or class that shall 
be executed within a transactional context.

If you’re using Spring without Spring Boot, you need to activate the transaction management by annotating
your application class with @EnableTransactionManagement.

-----------------------------------------------

The @Transactional annotation tells Spring that a transaction is required to execute this method. If you annotate some 
method in AuthorService class with @Transactional, When you inject the AuthorService somewhere, Spring generates 
a proxy object that wraps the AuthorService object and provides the required code to manage the transaction.

By default, that proxy starts a transaction before your request enters the first method that’s
annotated with @Transactional. After that method got executed, the proxy either commits the transaction or rolls
it back if a RuntimeException or Error occurred. Everything that happens in between, including all method calls,
gets executed within the context of that transaction

The @Transactional annotation supports a set of attributes that you can use to customize the behavior. 
The most important ones are propagation, readOnly, rollbackFor, and noRollbackFor

propagation - gives you control over the handling of existing and the creation of new transactions. 
If your method gets called within the context of an activate transaction, you can, for example, 
decide if your method shall join that transaction, create a new one, or fail.
Spring’s Propagation enum defines 7 values that you can provide to the propagation attribute 
of the @Transactional annotation. They enable you to control the handling of existing and creation of new transactions.
You can choose between:

REQUIRED to tell Spring to either join an active transaction or to start a new one if the method gets called 
without a transaction. This is the default behavior.
SUPPORTS to join an activate transaction if one exists. If the method gets called without an active transaction,
this method will be executed without a transactional context.
MANDATORY to join an activate transaction if one exists or to throw an Exception if the method gets called without 
an active transaction.
NEVER to throw an Exception if the method gets called in the context of an active transaction.
NOT_SUPPORTED to suspend an active transaction and to execute the method without any transactional context.
REQUIRES_NEW to always start a new transaction for this method. If the method gets called with an active transaction,
that transaction gets suspended until this method got executed.
NESTED to start a new transaction if the method gets called without an active transaction. If it gets called with 
an active transaction, Spring sets a savepoint and rolls back to that savepoint if an Exception occurs.

readOnly - use to improve the performance of read-only operations. If you want to implement a read-only operation, 
mark your transaction as read-only.
Since Spring 5.1, this sets Hibernate’s query hint org.hibernate.readOnly and avoids dirty checks on all retrieved entities

rollbackFor - enables you to provide an array of Exception classes for which the transaction shall be rolled back. 

noRollbackFor - accepts an array of Exception classes that shall not cause a rollback of the transaction.

----------------------------------------------------------------------------

Notes from https://www.baeldung.com/transaction-configuration-with-jpa-and-spring :

1. If we're using a Spring Boot project and have a spring-data-* or spring-tx dependencies on the classpath, 
then transaction management will be enabled by default.
2. Note that by default, rollback happens for runtime, unchecked exceptions only. The checked exception does not 
trigger a rollback of the transaction. We can, of course, configure this behavior with the rollbackFor and 
noRollbackFor annotation parameters.
3. What's important to keep in mind is that, if the transactional bean is implementing an interface, by default 
the proxy will be a Java Dynamic Proxy. This means that only external method calls that come in through the proxy 
will be intercepted. Any self-invocation calls will not start any transaction, even if the method has 
the @Transactional annotation.
4. Another caveat of using proxies is that only public methods should be annotated with @Transactional. 
Methods of any other visibilities will simply ignore the annotation silently as these are not proxied
5. The readOnly flag usually generates confusion, especially when working with JPA. From the Javadoc:
"This just serves as a hint for the actual transaction subsystem; it will not necessarily cause failure of write access 
attempts. A transaction manager which cannot interpret the read-only hint will not throw an exception when asked 
for a read-only transaction"
6. The fact is that we can't be sure that an insert or update won't occur when the readOnly flag is set. 
This behavior is vendor dependent, whereas JPA is vendor agnostic.