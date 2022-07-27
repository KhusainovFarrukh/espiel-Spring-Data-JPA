Cascade & CascadeTypes

todos

1. V learn about cascade in db
2. V learn about cascade type in spring
3. practice with each cascade type
4. orphanRemoval
5. write cascade.md

https://www.baeldung.com/jpa-cascade-types
https://www.scaler.com/topics/sql/cascade-in-sql/

CASCADE - Cascade in SQL is used to delete or update an entry from both the child and the parent table simultaneously.
The keyword CASCADE is used as conjunction while writing the query of ON DELETE or ON UPDATE. When a cascade keyword 
is added while writing a query for a parent table, then both the parent and child table get changed accordingly.

CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity

Cascade Type PERSIST propagates the persist operation from a parent to a child entity

CascadeType.MERGE propagates the merge operation from a parent to a child entity

CascadeType.REMOVE propagates the remove operation from parent to child entity. Similar to JPA's CascadeType.REMOVE, 
we have CascadeType.DELETE, which is specific to Hibernate. There is no difference between the two.

When we use CascadeType.DETACH, the child entity will also get removed from the persistent context

CascadeType.LOCK reattaches the entity and its associated child entity with the persistent context again

When we use this operation with Cascade Type REFRESH, the child entity also gets reloaded from the database
whenever the parent entity is refreshed

The replicate operation is used when we have more than one data source and we want the data in sync. 
With CascadeType.REPLICATE, a sync operation also propagates to child entities whenever performed on the parent entity

CascadeType.SAVE_UPDATE propagates the same operation to the associated child entity. 
It's useful when we use Hibernate-specific operations like save, update and saveOrUpdate