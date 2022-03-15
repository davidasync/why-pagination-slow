```
SELECT COUNT(*) FROM `sample_table`;

/**
Query time: 7.142 second(s), Number of affected records: 1000000
-> Table scan on sample_table  (cost=97693.75 rows=939663) (actual time=0.067..7998.369 rows=1000000 loops=1)
*/
EXPLAIN ANALYZE SELECT * FROM `sample_table`;

/**
Query time: 4 millisecond(s), Number of affected records: 10
-> Limit: 10 row(s)  (cost=97693.75 rows=10) (actual time=0.096..0.331 rows=10 loops=1)
    -> Table scan on sample_table  (cost=97693.75 rows=939663) (actual time=0.062..0.143 rows=10 loops=1)
*/
EXPLAIN ANALYZE SELECT * FROM `sample_table` LIMIT 10 OFFSET 0;

/**
Query time: 442 millisecond(s), Number of affected records: 10
-> Limit/Offset: 10/499990 row(s)  (cost=97693.75 rows=10) (actual time=7435.439..7435.614 rows=10 loops=1)
    -> Table scan on sample_table  (cost=97693.75 rows=939663) (actual time=0.054..3959.741 rows=500000 loops=1)
*/
EXPLAIN ANALYZE SELECT * FROM `sample_table` LIMIT 10 OFFSET 499990;

/**
Query time: 1.236 second(s), Number of affected records: 10
-> Limit/Offset: 10/999990 row(s)  (cost=97693.75 rows=0) (actual time=16690.944..16691.143 rows=10 loops=1)
    -> Table scan on sample_table  (cost=97693.75 rows=939663) (actual time=0.093..9096.920 rows=1000000 loops=1)
*/
EXPLAIN ANALYZE SELECT * FROM `sample_table` LIMIT 10 OFFSET 999990;

/**
Query time: 6 millisecond(s), Number of affected records: 1
-> Limit: 10 row(s)  (cost=2.36 rows=10) (actual time=0.113..0.578 rows=10 loops=1)
    -> Filter: (sample_table.id > 999990)  (cost=2.36 rows=10) (actual time=0.078..0.369 rows=10 loops=1)
        -> Index range scan on sample_table using PRIMARY over (999990 < id)  (cost=2.36 rows=10) (actual time=0.044..0.157 rows=10 loops=1)
*/
EXPLAIN ANALYZE SELECT * FROM `sample_table` where ID > 999990 order by ID limit 10
```
