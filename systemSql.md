# システム情報系のSQL

## テーブル確認
select owner, table_name, tablespace_name, pct_free, pct_used from dba_tables;

## 自分が所有するテーブル確認
select owner, table_name, tablespace_name, pct_free, pct_used  from dba_tables;

## テーブルの列定義確認
desc emp

## ユーザーが所有するテーブルの列に関する情報
select table_name, column_name, data_type, data_length, data_precision, data_scale, nullable, char_length, char_used from user_tab_columns where table_name = 'emp';

## インデックス確認
select owner, index_name, index_type, uniquencess, table_owner, table_name, tablesace_name from dba_indexes;

### ビットマップ索引の検索
select owner, index_name, index_type, table_owner, table_name, tablesace_name from dba_indexes where index_type = 'BITMAP';

### ファンクション索引の検索
select owner, index_name, index_type, table_owner, table_name, tablesace_name from dba_indexes where index_type like 'FUNCTION%';

## 索引列の確認
select index_owner, index_name, table_name, column_name from dba_ind_columns;

## ビューの確認
select owner, view_name, text from dba_views;

## ユーザーが所有するビューの確認
select view_name, text from users_views;

## マテビューの確認
select owner, mview_name, query, refresh_mode from dba_mviews;

## シーケンスの確認
select sequence_owner, sequence_name, min_value, increment_by from dba_sequences;

## シノニムの確認
select owner, synonym_name, table_owner, table_name from dba_synonyms;

