Relationships

todos:
+ create new package for relationships
+ practice & read annotation comment-docs
+ simple youtube tutorials
+ bidirectional
+ Thorben Janssen best practices video (about bidirectional)
6. Cascade, Fetch and other annotations/properties
7. todos in relationships package

https://thorben-janssen.com/entity-mappings-introduction-jpa-fetchtypes/

FetchType

FetchType - will tell SQL whether to fetch associated entities of main entity when fetching the main entity,
or fetching them when they needed.

LAZY - fetch associated entities when they needed, when relationship is used

EAGER - fetch associated entities of main entity when fetching the main entity, in one sql select statement