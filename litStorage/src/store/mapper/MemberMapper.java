package store.mapper;

import java.util.List;

import domain.Member;

public interface MemberMapper {
	int insertMember(Member member);
	Member selectMemberById(String id);
	int updateMember(Member member);
	int deleteMember(String id);
	List<Member> selectMembersByName(String name);
}
