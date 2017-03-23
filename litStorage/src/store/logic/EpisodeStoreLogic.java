package store.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Episode;
import store.facade.EpisodeStore;
import store.mapper.EpisodeMapper;

public class EpisodeStoreLogic implements EpisodeStore {

	SqlSessionFactory factory;

	public EpisodeStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertEpisode(Episode episode) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.insertEpisode(episode);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean updateEpisode(Episode episode) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.updateEpisode(episode);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean deleteEpisode(String episodeId) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.deleteEpisode(episodeId);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public Episode selectEpisodeById(String id) {
		SqlSession session = factory.openSession();
		Episode episode = null;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			episode = mapper.selectEpisodeById(id);

		} finally {
			session.close();
		}
		return episode;
	}

	@Override
	public List<Episode> selectEpisodesByLiteratureId(String literatureId) {
		SqlSession session = factory.openSession();
		List<Episode> list = new ArrayList<>();
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			list = mapper.selectEpisodesByLiteratureId(literatureId);

		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean updateBound(String bound) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.updateBound(bound);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}

}
