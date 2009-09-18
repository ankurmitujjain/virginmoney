This project contains all rewrite rules for the Virgin Money Giving Application.

frontend is deployed to customer facing websevers
common is deployed to customer and internal facing webservers
admin is deployed to internal facing webservers only

Ordering is important as is driver by the first to characters of the filename:

Example frontend configuration list:

Include conf.d/shared/01_common_first.conf
Include conf.d/shared/50_frontend.conf
Include conf.d/shared/99_common_last.conf

Example admin configuration list:

Include conf.d/shared/01_common_first.conf
Include conf.d/shared/50_admin.conf
Include conf.d/shared/99_common_last.conf

This should be applied into vhosts as appropriate - uk.*.virginmoneygiving.com.
