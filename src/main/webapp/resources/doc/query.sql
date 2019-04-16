-- �Խ��� �޴�
select b.bcode, b.bname, b.btype, c.ccode, c.cname,
		case 
			when b.btype = 5 then 'reboard'
			when b.btype = 6 then 'album'
			when b.btype = 7 then 'bbs'
			else 'board'
		end control
from board_list b, category c
where b.ccode = c.ccode
order by bcode asc

-- �۸��
select b.* 
from ( 
	select rownum rn, a.* 
	from ( 
		select b.seq, b.id, b.name, b.email, b.subject, b.content, b.hit, b.bcode, 
				r.rseq, r.ref, r.lev, r.step, r.pseq, r.reply, 
 				case  
					when to_char(logtime, 'yymmdd') = to_char(sysdate, 'yymmdd') 
					then to_char(logtime, 'hh24:mi:ss') 
					else to_char(logtime, 'yy.mm.dd') 
				end logtime 
		from board b, reboard r 
		where b.seq = r.seq 
		and b.bcode = ? 
		order by seq desc 
		) a 
	where rownum <= ? 
	) b 
where b.rn > ? 

-- �۹�ȣ ���
select board_seq.nextval 
from dual


-- ���۾���(�亯��)
insert all 
	into board (seq, name, id, email, subject, content, hit, logtime, bcode) 
	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) 
	into reboard (rseq, seq, ref, lev, step, pseq, reply) 
	values (reboard_rseq.nextval, ?, ?, 0, 0, 0, 0) 
select * from dual



-- ���� ����
select count(seq) 
from board
where bcode = 3
and to_char(logtime, 'yymmdd') = to_char(sysdate, 'yymmdd')

-- ��ü�� ����
select count(seq) 
from board
where bcode = 3




