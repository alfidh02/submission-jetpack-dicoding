## Skenario Unit Testing Aplikasi TVMov

> MovieViewModelTest
    - Memuat data Movie :
        1. Memberikan data manipulasi untuk nilai kembalian dari fungsi getMovies di kelas repository.
        2. Memastikan fungsi getMovies terpanggil di kelas repository.
        3. Memastikan data Movies (movieEntity) tidak kosong.
        4. Memastikan data Movies sesuai dengan yang diharapkan.
        5. Memastikan jumlah data Movies sesuai dengan yang diharapkan.

> TVViewModelTest
    - Memuat data TV Show :
        1. Memberikan data manipulasi untuk nilai kembalian dari fungsi getTV di kelas repository.
        2. Memastikan fungsi getTV terpanggil di kelas repository.
        3. Memastikan data TV Show (tvEntity) tidak kosong.
        4. Memastikan data TV Show sesuai dengan yang diharapkan.
        5. Memastikan jumlah data TV Show sesuai dengan yang diharapkan.
        
> DetailViewModelTest
    - Memuat data detail Movie dan TV Show :
        1. Memberikan data manipulasi untuk nilai kembalian dari fungsi getDetailTV dan getDetailMovies di kelas repository.
        2. Memastikan nilai kembalian dari fungsi setDetailTV dan setDetailMovie pada DetailViewModel tetap diupdate.
        3. Memastikan data movie dan tvShow telah berubah.

* FavoriteViewModelTest
    - Memuat data Favorite :
        1. Memberikan data manipulasi untuk nilai kembalian dari fungsi getMoviesFav dan getTVFav di kelas repository.
        2. Memastikan fungsi getMoviesFav dan getTVFav terpanggil di kelas repository.
        3. Memastikan data Favorite (favMovie dan favTVShow) dari fungsi viewModel tidak kosong.
        4. Memastikan jumlah data Favorite sesuai dengan yang diharapkan.