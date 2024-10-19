// async function getPageData(url, num = 0, size = 20, sort = '') {
//     let url2 = ''
//     if (sort === '') {
//         url2 = `${url}?page=${num}&size=${size}`
//     } else {
//         url2 = `${url}?page=${num}&size=${size}&sort=${sort}`
//     }
//     const options = {
//         method: 'GET',
//         headers: {
//             'accept': 'application/hal+json'
//         },
//         body: JSON.stringify({})
//     };
//     const response = await fetch(url2, options);
//     if (!response.ok) {
//         throw new Error(`HTTP error! status: ${response.status}`);
//     }
//     const data = await response.json();
//     console.log("page 数据: " + data);
//     return data;
// }
//
// async function getTotalPageNum(url) {
//     data = await getPageData(url)
//     return data.page.totalPages
// }
