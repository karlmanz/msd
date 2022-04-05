create view view_notification as
select nvl(dm_update_by, dm_enter_by) as notify_from,
dr_supervisor_id as notify_to,
dm_id  as notify_doc_id,
dm_title as notify_title,
dm_status  as notify_status,
dm_status as notify_action,
nvl(dm_update_date, dm_enter_date) as notify_date
from document_main, document_review
where dm_id = dr_document_id
and dm_status = 'Submitted'
UNION
select dr_supervisor_id as notify_from,
nvl(dm_update_by, dm_enter_by) as notify_to,
dm_id  as notify_doc_id,
dm_title as notify_title,
dm_status  as notify_status,
dm_status as notify_action,
nvl(dm_update_date, dm_enter_date) as notify_date
from document_main, document_review
where dm_id = dr_document_id
and dm_status = 'Approved'
and dr_comment is not null