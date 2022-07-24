Entity lifecycle

https://www.youtube.com/watch?v=Y7PpjerZkc0&t
https://www.youtube.com/watch?v=j6MHydQrXoE
https://www.youtube.com/watch?v=GOdh_6pAyYE

A persistence context is a set of entity instances in which for any persistent entity identity
there is a unique entity instance. Within the persistence context, the entity instance and their
lifecycle are managed.

The lifecycle model consists of 4 stages: transient, managed (persisted/persistent), removed, detached

TRANSIENT - state of newly instantiated entity object. Entity not persisted, so it does not
represent any database record.
Your persistence context does not know about your newly instantiate object.
Because of that, it doesn’t automatically perform an SQL INSERT statement or tracks any changes.
As long as your entity object is in the lifecycle state transient, you can think of it as
a basic Java object without any connection to the database and any JPA-specific functionality.

MANAGED (PERSISTED/PERSISTENT) - When providing entity in transient state to Entity manager, the entity object then changes its
lifecycle state to managed and gets attached to the current persistence context.
All entity objects attached to the current persistence context are in the lifecycle state managed.
That means that your persistence provider, for example Hibernate,
will detect any changes on the objects generate the required SQL INSERT or UPDATE statements
when it flushes the persistence context.

DETACHED - An entity that was previously managed but is no longer attached to the current persistence context
is in the lifecycle state detached. Changes to such an entity object will not be persisted in the database.
An entity gets detached when you close the persistence context.
That typically happens after a request got processed. Then the database transaction gets committed,
the persistence context gets closed, and the entity object gets returned to the caller.
The caller then retrieves an entity object in the lifecycle state detached.
There are only very few performance tuning reasons to detach a managed entity.
If you decide to detach an entity, you should first flush the persistence context to avoid losing any pending changes.

!!! DETACHED and TRANSIENT are both outside of persistence context, but main difference between them is that
entity in detached state has record in db (and has valid primary key), but entity in transient state has not record
in db (in most cases there is no primary key)

REMOVED - When you call the remove method on your EntityManager, the mapped database record doesn’t get removed immediately.
The entity object only changes its lifecycle state to removed. During the next flush operation,
Hibernate will generate an SQL DELETE statement to remove the record from the database table.

Transient -> Managed: EntityManager.persist(entity)
Managed -> DB: EntityManager.flush()
DB -> Managed: EntityManger.find(...), any query
Managed -> Detached: EntityManager.detach(entity), EntityManager.evict(entity), clear(), close()
Detached -> Managed: EntityManager.update(entity), EntityManager.merge(entity)