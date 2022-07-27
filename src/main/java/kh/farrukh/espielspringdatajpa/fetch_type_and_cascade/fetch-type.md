https://thorben-janssen.com/entity-mappings-introduction-jpa-fetchtypes/

FetchType

FetchType - will tell SQL whether to fetch associated entities of main entity when fetching the main entity,
or fetching them when they needed.
The default depends on the cardinality of the relationship. All to-one relationships use FetchType.EAGER and all to-many relationships FetchType.LAZY.

LAZY - fetch associated entities when they needed, when relationship is used

EAGER - fetch associated entities of main entity when fetching the main entity, in one sql select statement