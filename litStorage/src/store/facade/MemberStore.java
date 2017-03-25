package store.facade;

import java.util.List;

import domain.Member;

public interface MemberStore {
	boolean insertMember(Member member);
	Member selectMemberById(String id);
	boolean updateMember(Member member);
	boolean deleteMember(String id);
	List<Member> selectMembersByName(String name);
}
