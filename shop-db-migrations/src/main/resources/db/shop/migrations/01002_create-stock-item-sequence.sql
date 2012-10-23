CREATE SEQUENCE stock_item_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;
  
GRANT SELECT					ON stock_item_seq TO flowfx_app_fullaccess_role;
GRANT SELECT					ON stock_item_seq TO flowfx_support_select_role;  
